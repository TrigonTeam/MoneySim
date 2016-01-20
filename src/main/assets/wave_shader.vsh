uniform mat4 pMat;

attribute vec2 vPos;
attribute vec4 vColor;

varying vec4 fColor;
varying vec2 fPos;

void main() {
    vec4 pos = pMat * vec4(vPos, 0.0, 1.0);

    gl_Position = pos;

    fColor = vColor;
    fPos = pos.xy;
    float aspect = abs(pMat[1].y / pMat[0].x);
    fPos.x *= aspect;
}