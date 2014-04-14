Graphics3D 640,480
SetBuffer BackBuffer()

cam1=CreateCamera()
cam2=CreateCamera()
cam3=CreateCamera()
cam4=CreateCamera()

light=CreateLight()

; camera position values (unimportant)
cx#=0
cy#=0
cz#=-5

CameraClsColor cam1,255,0,0
CameraClsColor cam2,0,255,0
CameraClsColor cam3,0,0,255
CameraClsColor cam4,255,255,0

CameraViewport cam1,77,19,212,140
CameraViewport cam2,397,19,212,140
CameraViewport cam3,77,223,212,140
CameraViewport cam4,397,223,212,140

PositionEntity cam1,cx#,cy#,cz#
PositionEntity cam2,cx#,cy#,cz#
PositionEntity cam3,cx#,cy#,cz#
PositionEntity cam4,cx#,cy#,cz#

sphere1=CreateSphere() : PositionEntity sphere1,-3,0,0
sphere2=CreateSphere() : PositionEntity sphere2,-1,0,0
sphere3=CreateSphere() : PositionEntity sphere3,1,0,0
sphere4=CreateSphere() : PositionEntity sphere4,3,0,0

Dim sphere (4)
sphere(1)=sphere1
sphere(2)=sphere2
sphere(3)=sphere3
sphere(4)=sphere4

Dim cam (4)
cam(1)=cam1
cam(2)=cam2
cam(3)=cam3
cam(4)=cam4

port1=CreateImage(212,140)
port2=CreateImage(212,140)
port3=CreateImage(212,140)
port4=CreateImage(212,140)

;Variables
monselected=1

guilayer=LoadImage("gui.bmp")

cursor=LoadImage("cursor.png")
MaskImage cursor,0,17,119

HidePointer

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)

If KeyDown( 200 )=True Then MoveEntity sphere1,0,0.1,0
If KeyDown( 208 )=True Then MoveEntity sphere1,0,-0.1,0

;Checking for mouseclicks over the GUI
buttonpress=0 ; Cancel all button presses first
If MouseDown(1)
If MouseX()>28 And MouseX()<100 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=1 : If monselected<4 : monselected=monselected+1 : Else : monselected=1 : EndIf : mouseclick=1
If MouseX()>28 And MouseX()<100 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=2 : mouseclick=1
If MouseX()>128 And MouseX()<176 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=3 : mouseclick=1
If MouseX()>128 And MouseX()<176 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=4 : mouseclick=1
If MouseX()>192 And MouseX()<240 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=5 : mouseclick=1
If MouseX()>192 And MouseX()<240 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=6 : mouseclick=1
If MouseX()>256 And MouseX()<278 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=7 : mouseclick=1
If MouseX()>282 And MouseX()<304 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=8 : mouseclick=1
If MouseX()>256 And MouseX()<304 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=9 : mouseclick=1
If MouseX()>364 And MouseX()<388 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=10 : mouseclick=1
If MouseX()>332 And MouseX()<356 And MouseY()>444 And MouseY()<457 And mouseclick=0 Then buttonpress=11 : mouseclick=1
If MouseX()>396 And MouseX()<420 And MouseY()>444 And MouseY()<457 And mouseclick=0 Then buttonpress=12 : mouseclick=1
If MouseX()>364 And MouseX()<388 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=13 : mouseclick=1
If MouseX()>464 And MouseX()<496 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=14 : mouseclick=1
If MouseX()>512 And MouseX()<576 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=15 : mouseclick=1
If MouseX()>592 And MouseX()<624 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=16 : mouseclick=1
If MouseX()>464 And MouseX()<528 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=17 : mouseclick=1
If MouseX()>544 And MouseX()<624 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=18 : mouseclick=1
Else
	mouseclick=0
EndIf


For i = 1 To 4

	ShowEntity sphere(i)

	If i=1
		HideEntity sphere(2) : HideEntity sphere(3) : HideEntity sphere(4)
		GrabImage port1,77,19
	ElseIf i=2
		HideEntity sphere(1) : HideEntity sphere(3) : HideEntity sphere(4)
		GrabImage port2,397,19
	ElseIf i=3
		HideEntity sphere(1) : HideEntity sphere(2) : HideEntity sphere(4)
		GrabImage port3,77,223
	Else
		HideEntity sphere(1) : HideEntity sphere(2) : HideEntity sphere(3)
		GrabImage port4,397,223
	EndIf
	
	Cls
	
	DrawImage port1,77,19
	DrawImage port2,397,19
	DrawImage port3,77,223
	DrawImage port4,397,223
		
	DrawImage guilayer,0,0

	DrawImage cursor,MouseX()-7,MouseY()-2
	
	Text 0,0,MouseX()+" , "+MouseY()
	Text 0,10,monselected
	
	Flip
	
	UpdateWorld
	RenderWorld

Next
 
Wend

End