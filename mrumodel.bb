Graphics3D 640,480
SetBuffer BackBuffer()

camera=CreateCamera()
light=CreateLight()

; camera position values (ignore)
cx#=7
cy#=7
cz#=-10

mrutex=LoadTexture("mrutex.bmp")
mru=buildmru()
EntityTexture mru,mrutex

EntityBlend mru,0


;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)

;camera rubbish
; Change position values depending on key pressed
If KeyDown( 203 )=True Then TurnEntity mru,0,0.5,0
If KeyDown( 205 )=True Then TurnEntity mru,0,-0.5,0
If KeyDown( 208 )=True Then TurnEntity mru,0.5,0,0
If KeyDown( 200 )=True Then TurnEntity mru,-0.5,0,0
If KeyDown( 44 )=True Then cz#=cz#-0.1
If KeyDown( 30 )=True Then cz#=cz#+0.1
PositionEntity camera,cx#,cy#,cz#
RotateEntity camera,0,0,0

ScaleEntity mru,-1,1,-1

UpdateWorld
RenderWorld

Text 0,0,"X= "+cx#
Text 0,10,"Y= "+cy#
Text 0,20,"Z= "+cz#

Flip

Wend

End

;FUNCTIONS
;------------
;Floor Pieces:
;------------
Function Buildmru()

he1=CreateBrush(255,255,255)

bodyright=CreateMesh() ; Main mesh
b1=CreateSurface(bodyright,he1)
;the body *******************************
;right points
AddVertex ( b1,5,9,2.5,     0,0.65) ; Point 0
AddVertex ( b1,5.5,9.5,2.5, 0,0.75)
AddVertex ( b1,8,9.5,2.5,   0.16,0.75)
AddVertex ( b1,8,6,2.5,     0.16,0.35)
AddVertex ( b1,9,5.5,2.5,   0.16,0.3)
AddVertex ( b1,9,5,2.5,     0.16,0.25)
AddVertex ( b1,5,5,2.5,     0,0.25)
;duplicate side coords of 0,1 and 6 (for u,v)
;back points
AddVertex ( b1,4,9,6,       0.76,0.65) ; Point 7
AddVertex ( b1,5,10,6,      0.76,0.75)
AddVertex ( b1,5,10,4,      0.92,0.75)
AddVertex ( b1,4,9,4,       0.92,0.65)
AddVertex ( b1,4,5,4,       0.92,0.25)
AddVertex ( b1,4,5,6,       0.76,0.25)
;left points
AddVertex ( b1,8,9.5,7.5,   0.5,0.75) ; Point 13
AddVertex ( b1,5.5,9.5,7.5, 0.67,0.75)
AddVertex ( b1,5,9,7.5,     0.67,0.65)
AddVertex ( b1,5,5,7.5,     0.67,0.25)
AddVertex ( b1,9,5,7.5,     0.5,0.25)
AddVertex ( b1,9,5.5,7.5,   0.5,0.3)
AddVertex ( b1,8,6,7.5,     0.5,0.35)
;front points
AddVertex ( b1,8,10,4,      0.25,0.75) ; Point 20
AddVertex ( b1,8,10,6,      0.42,0.75)
AddVertex ( b1,9,9.5,6,     0.42,0.65)
AddVertex ( b1,9,6,6,       0.42,0.35)
AddVertex ( b1,10,5.5,6,    0.42,0.3)
AddVertex ( b1,10,5,6,      0.42,0.25)
AddVertex ( b1,10,5,4,      0.25,0.25)
AddVertex ( b1,10,5.5,4,    0.25,0.3)
AddVertex ( b1,9,6,4,       0.25,0.35)
AddVertex ( b1,9,9.5,4,     0.25,0.65)
;duplicate side coords of 0,1 and 6 (for uv)
AddVertex ( b1,5,9,2.5,     1,0.75) ; Point 30
AddVertex ( b1,5.5,9.5,2.5, 1,0.75)
AddVertex ( b1,5,5,2.5,     1,0.25)
;the head *******************************
;right points
AddVertex ( b1,4,13,3,      0,1) ; Point 33 
AddVertex ( b1,9,13,3,      0.3,1)
AddVertex ( b1,9,12.5,2,    0.25,0.91)
AddVertex ( b1,9,11,2,      0.25,0.83)
AddVertex ( b1,9,10.5,3,    0.3,0.75)
AddVertex ( b1,4,10.5,3,    0,0.75)
AddVertex ( b1,4,11,2,      0,0.83)
AddVertex ( b1,4,12.5,2,    0,0.91)
;back points
AddVertex ( b1,4,13,7,      0.8,1) ; Point 41
AddVertex ( b1,4,13,3,      0.95,1)
AddVertex ( b1,3,12.5,3,    0.95,0.91)
AddVertex ( b1,3,11,3,      0.95,0.83)
AddVertex ( b1,4,10.5,3,    0.95,0.75)
AddVertex ( b1,4,10.5,7,    0.8,0.75)
AddVertex ( b1,3,11,7,      0.8,0.83)
AddVertex ( b1,3,12.5,7,    0.8,0.91)
;left points
AddVertex ( b1,9,13,7,      0.45,1) ; Point 49
AddVertex ( b1,4,13,7,      0.75,1)
AddVertex ( b1,4,12.5,8,    0.75,0.91)
AddVertex ( b1,4,11,8,      0.75,0.83)
AddVertex ( b1,4,10.5,7,    0.75,0.75)
AddVertex ( b1,9,10.5,7,    0.45,0.75)
AddVertex ( b1,9,11,8,      0.5,0.83)
AddVertex ( b1,9,12.5,8,    0.5,0.91)
;duplicate points of 33, 40, 39 and 38 (for uv)
AddVertex ( b1,4,13,3,      1,1)  ; Point 57
AddVertex ( b1,4,12.5,2,    1,0.91)
AddVertex ( b1,4,11,2,      1,0.83)
AddVertex ( b1,4,10.5,3,    1,0.75)
;the skirt ******************************
;right points
AddVertex ( b1,1,4,0,       0.05,0.25)  ; Point 61
AddVertex ( b1,14,4,0,      0.2,0.25)
AddVertex ( b1,15,3,0,      0.25,0.13)
AddVertex ( b1,15,2,0,      0.25,0)
AddVertex ( b1,0,2,0,       0,0)
AddVertex ( b1,0,3,0,       0,0.13)
;back points
AddVertex ( b1,0,4,9,       0.8,0.25) ; Point 67
AddVertex ( b1,0,4,1,       0.95,0.25)
AddVertex ( b1,0,3,0,       1,0.13)
AddVertex ( b1,0,2,0,       1,0)
AddVertex ( b1,0,2,10,      0.75,0)
AddVertex ( b1,0,3,10,      0.75,0.13)
;left points
AddVertex ( b1,14,4,10,     0.55,0.25) ; Point 73
AddVertex ( b1,1,4,10,      0.7,0.25)
AddVertex ( b1,0,3,10,      0.75,0.13)
AddVertex ( b1,0,2,10,      0.75,0)
AddVertex ( b1,15,2,10,     0.5,0)
AddVertex ( b1,15,3,10,     0.5,0.13)
;front points
AddVertex ( b1,15,4,1,      0.275,0.25) ; Point 79 ( u was 0.3)
AddVertex ( b1,15,4,9,      0.475,0.25) ;          ( v was 0.45)
AddVertex ( b1,15,3,10,     0.5,0.13)    ;          ( v was 0.2)
AddVertex ( b1,15,2,10,     0.5,0)
AddVertex ( b1,15,2,0,      0.25,0)
AddVertex ( b1,15,3,0,      0.25,0.13)   ;          ( v was 0.2)

;triangles ******************************
;right side
AddTriangle( b1,0,1,2)
AddTriangle( b1,0,2,3)
AddTriangle( b1,3,6,0)
AddTriangle( b1,3,4,6)
AddTriangle( b1,4,5,6)
;back
AddTriangle( b1,7,8,9)
AddTriangle( b1,7,9,10)
AddTriangle( b1,10,11,12)
AddTriangle( b1,10,12,7)
;right back corner
AddTriangle( b1,10,9,31)
AddTriangle( b1,10,31,30)
AddTriangle( b1,10,30,32)
AddTriangle( b1,10,32,11)
;left side
AddTriangle( b1,13,14,15)
AddTriangle( b1,13,15,16)
AddTriangle( b1,16,19,13)
AddTriangle( b1,16,17,19)
AddTriangle( b1,17,18,19)
;left back corner
AddTriangle( b1,15,14,8)
AddTriangle( b1,8,7,15)
AddTriangle( b1,7,12,16)
AddTriangle( b1,7,16,15)
;front
AddTriangle( b1,20,21,22)
AddTriangle( b1,20,22,29)
AddTriangle( b1,22,23,29)
AddTriangle( b1,23,28,29)
AddTriangle( b1,23,24,28)
AddTriangle( b1,24,27,28)
AddTriangle( b1,24,25,27)
AddTriangle( b1,25,26,27)
;front left corner
AddTriangle( b1,21,13,22)
AddTriangle( b1,22,13,19)
AddTriangle( b1,22,19,23)
AddTriangle( b1,23,19,18)
AddTriangle( b1,23,18,24)
AddTriangle( b1,24,18,17)
AddTriangle( b1,24,17,25)
;front right corner
AddTriangle( b1,2,20,29)
AddTriangle( b1,2,29,28)
AddTriangle( b1,2,28,3)
AddTriangle( b1,3,28,27)
AddTriangle( b1,3,27,4)
AddTriangle( b1,4,27,26)
AddTriangle( b1,4,26,5)
;the head ************************
;right side
AddTriangle( b1,33,34,35)
AddTriangle( b1,33,35,40)
AddTriangle( b1,40,35,36)
AddTriangle( b1,40,36,39)
AddTriangle( b1,39,36,37)
AddTriangle( b1,39,37,38)
;back
AddTriangle( b1,41,42,43)
AddTriangle( b1,41,43,48)
AddTriangle( b1,48,43,44)
AddTriangle( b1,48,44,47)
AddTriangle( b1,47,44,45)
AddTriangle( b1,47,45,46)
;right side corner
AddTriangle( b1,57,58,43)
AddTriangle( b1,43,58,59)
AddTriangle( b1,43,59,44)
AddTriangle( b1,44,59,60)
;left side
AddTriangle( b1,49,50,51)
AddTriangle( b1,49,51,56)
AddTriangle( b1,56,51,52)
AddTriangle( b1,56,52,55)
AddTriangle( b1,55,52,53)
AddTriangle( b1,55,53,54)
;left side corner
AddTriangle( b1,41,48,51)
AddTriangle( b1,51,48,47)
AddTriangle( b1,51,47,52)
AddTriangle( b1,52,47,46)
;front
AddTriangle( b1,34,49,56)
AddTriangle( b1,34,56,35)
AddTriangle( b1,35,56,55)
AddTriangle( b1,35,55,36)
AddTriangle( b1,36,55,54)
AddTriangle( b1,36,54,37)
;the skirt ***********************
;right side
AddTriangle( b1,61,62,63)
AddTriangle( b1,61,63,66)
AddTriangle( b1,66,63,64)
AddTriangle( b1,66,64,65)
;back side
AddTriangle( b1,67,68,69)
AddTriangle( b1,67,69,72)
AddTriangle( b1,72,69,70)
AddTriangle( b1,72,70,71)
;left side
AddTriangle( b1,73,74,75)
AddTriangle( b1,73,75,78)
AddTriangle( b1,78,75,76)
AddTriangle( b1,78,76,77)
;front side
AddTriangle( b1,79,80,81)
AddTriangle( b1,79,81,84)
AddTriangle( b1,84,81,82)
AddTriangle( b1,84,82,83)

;bodycorner1=CreateMesh() ; Body Corner
;he2=CreateBrush(255,0,0)
;b3=CreateSurface(bodycorner1,he2)
;FreeBrush he2
;AddVertex ( b3,4,9,6,0,0)
;AddVertex ( b3,5,10,6,0,0)
;AddVertex ( b3,5,10,4,0,0)
;AddVertex ( b3,4,9,4,0,0)
;AddVertex ( b3,4,5,4,0,0)
;AddVertex ( b3,4,5,6,0,0)
;AddTriangle( b3,0,1,2)
;AddTriangle( b3,0,2,3)
;AddTriangle( b3,3,4,5)
;AddTriangle( b3,3,5,0)

FreeBrush he1

Return bodyright

End Function