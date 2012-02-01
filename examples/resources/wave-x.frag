#version 110

uniform sampler2D texture;

const float pi = 3.141592654;

uniform float amplitude;
uniform float wavelen;
uniform float offset;

void main()
{
	vec2 v = vec2(gl_TexCoord[0]);
	
	float x = v.x + offset;
	float s = sin(2.0 * pi * x / wavelen);
	v.y = v.y - s * amplitude;

	gl_FragColor = gl_Color * texture2D(texture, v);
}
