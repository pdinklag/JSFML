//SFML config
#include <SFML/Config.hpp>

//Java AWT
#if defined(SFML_SYSTEM_WINDOWS)
	#include <hash_map>
	#include <win32/jawt_md.h>
#elif defined(SFML_SYSTEM_LINUX)
	#include <linux/jawt_md.h>
#elif defined(SFML_SYSTEM_MACOS)
	//TODO what is the jawt_md.h include path on Mac OS X?
#endif

//JNI
#include <JSFML/JNI/org_jsfml_graphics_RenderCanvas.h>

//SFML
#if defined(SFML_SYSTEM_LINUX)
	#if defined(None)
		#undef None //not sure what defines this, but it caused trouble on Linux
	#endif
#endif

#include <SFML/Graphics.hpp>

#if defined(SFML_SYSTEM_WINDOWS)
	/**
	* On Windows, there is a conflict between SFML and the Java AWT since they both use the
	* GWLP_USERDATA field to store information. This window proc will be used instead to delegate
	* all messages to both SFML and AWT with their respective user data set correctly.
	*
	* The NoOp window proc is used as SFML's "myCallback" method and doesn't do anything it all,
	* but has to be used so SFML doesn't use the default window proc.
	*/

	//window proc and user data storage
	struct WndProcStruct {
		LONG_PTR awtUserData;
		WNDPROC awtWindowProc;

		LONG_PTR sfmlUserData;
		WNDPROC sfmlWindowProc;
	};

    //we may have multiple RenderCanvas objects, so map the window data storages to their handles
	typedef stdext::hash_map<HWND, WndProcStruct> WndProcMap;
	WndProcMap wndProcs;

    //the delegation window proc that feeds AWT and SFML with what they need
	LRESULT CALLBACK WindowProc(HWND handle, UINT message, WPARAM wParam, LPARAM lParam) {
		WndProcMap::iterator it = wndProcs.find(handle);
		if(it != wndProcs.end()) {
			//AWT
			LRESULT awtResult = CallWindowProc(it->second.awtWindowProc, handle, message, wParam, lParam);

			//SFML
			SetWindowLongPtr(handle, GWLP_USERDATA, it->second.sfmlUserData);
			if(message != WM_CREATE && message != WM_CLOSE)
				CallWindowProc(it->second.sfmlWindowProc, handle, message, wParam, lParam);

			SetWindowLongPtr(handle, GWLP_USERDATA, it->second.awtUserData);

			return awtResult;
		}
		else {
			return 0;
		}
	}

    //simple override for the default window proc that doesn't do anything
	LRESULT CALLBACK NoOpWindowProc(HWND handle, UINT message, WPARAM wParam, LPARAM lParam) {
		return 0;
	}
#endif

/**
 * Gets the handle from an AWT canvas.
 *
 * Note that the use of the java.awt.Canvas class is required, because it's a heavyweight component.
 * Unlike lightweight components (as used in Swing), heavyweight components get their own handle.
 */
sf::WindowHandle getCanvasHandle(JNIEnv *env, jobject canvas) {
	sf::WindowHandle handle = 0;

	JAWT awt;
	awt.version = JAWT_VERSION_1_4;

	if(JAWT_GetAWT(env, &awt)) {
		JAWT_DrawingSurface* ds = awt.GetDrawingSurface(env, canvas);

		if(ds) {
			if(!(ds->Lock(ds) & JAWT_LOCK_ERROR)) {
				JAWT_DrawingSurfaceInfo* dsi = ds->GetDrawingSurfaceInfo(ds);
				if(dsi) {
					#if defined(SFML_SYSTEM_WINDOWS)
						JAWT_Win32DrawingSurfaceInfo* dsi_win = (JAWT_Win32DrawingSurfaceInfo*)dsi->platformInfo;
						if(dsi_win) handle = dsi_win->hwnd;
					#elif defined(SFML_SYSTEM_LINUX)
						JAWT_X11DrawingSurfaceInfo* dsi_x11 = (JAWT_X11DrawingSurfaceInfo*)dsi->platformInfo;
						if(dsi_x11) handle = dsi_x11->drawable;
					#elif defined(SFML_SYSTEM_MACOS)
						JAWT_MacOSXDrawingSurfaceInfo* dsi_mac = (JAWT_MacOSXDrawingSurfaceInfo*)dsi->platformInfo;
						if(dsi_mac) handle = dsi_mac->cocoaViewRef;
					#endif
				}
				ds->Unlock(ds);
			}
			awt.FreeDrawingSurface(ds);
		}
	}

	return handle;
}

/*
 * Class:     org_jsfml_graphics_RenderCanvas
 * Method:    nativeCreateRenderWindow
 * Signature: (Ljava/nio/Buffer;)J
 */
JNIEXPORT jlong JNICALL Java_org_jsfml_graphics_RenderCanvas_nativeCreateRenderWindow
    (JNIEnv *env, jobject obj, jobject paramsBuffer) {
    
    jint *params = (jint*)env->GetDirectBufferAddress(paramsBuffer);
    sf::ContextSettings context(params[0], params[1], params[2], params[3], params[4]);
    
	sf::WindowHandle handle = getCanvasHandle(env, obj);

	if(handle) {
		#if defined(SFML_SYSTEM_WINDOWS)
			WndProcStruct* proc = &wndProcs[handle];
			//TODO remove from map upon destruction

			proc->awtUserData = GetWindowLongPtr(handle, GWLP_USERDATA);
			proc->awtWindowProc = (WNDPROC)GetWindowLongPtr(handle, GWLP_WNDPROC);

			SetWindowLongPtr(handle, GWLP_WNDPROC, (LONG_PTR)&NoOpWindowProc); //make SFML use the NoOp as "myCallback"
		#endif

		sf::RenderWindow* window = new sf::RenderWindow(handle, context);

		#if defined(SFML_SYSTEM_WINDOWS)
			proc->sfmlUserData = GetWindowLongPtr(handle, GWLP_USERDATA);
			proc->sfmlWindowProc = (WNDPROC)GetWindowLongPtr(handle, GWLP_WNDPROC);

			SetWindowLongPtr(handle, GWLP_WNDPROC, (LONG_PTR)&WindowProc);
			SetWindowLongPtr(handle, GWLP_USERDATA, proc->awtUserData);
		#endif

		return (jlong)window;
	} else {
		return 0;
	}
}
