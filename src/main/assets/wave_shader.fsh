precision highp float;

#define M_PI 3.1415926535897932384626433832795

varying vec4 fColor;
varying vec2 fPos;

uniform float time;

void main() {

    float timeS = time / 60.0;

    float delayFactorR = sin((fPos.x + timeS * 0.7) * (M_PI / 2.0));
    float delayFactorG = sin((fPos.x + timeS * 0.7) * (M_PI / 2.0));
    float delayFactorB = sin((fPos.x + timeS * 0.7) * (M_PI / 2.0));

    float r = ((sin(((timeS * 2.3 - delayFactorR) * 1.3 + fPos.y * 2.0)) + 1.0) / 2.0) * fColor.r;
    float g = ((sin(((timeS * 3.3 - delayFactorG) * 1.6 + fPos.y * 1.0)) + 1.0) / 2.0) * fColor.g;
    float b = ((sin(((timeS * 2.8 - delayFactorB) * 1.9 + fPos.y * 3.0)) + 1.0) / 2.0) * fColor.b;

    gl_FragColor = vec4(r, g, b, 1.0);
}