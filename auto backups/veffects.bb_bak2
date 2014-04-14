Graphics3D 640,480,32,1
SetBuffer BackBuffer()

camera=CreateCamera()

CameraViewport camera,77,19,212,140

CameraClsColor camera,135,135,135

light=CreateLight()

; camera position values (unimportant)
cx#=0
cy#=0
cz#=-5

PositionEntity camera,cx#,cy#,cz#

sphere1=CreateSphere() : PositionEntity sphere1,0,0,0

image=CreateImage(212,140)

SeedRnd MilliSecs() 

;vhold variables
vholdpos#=0
vholdspeed#=Int(Rnd(-8,10))
bandheight=12

cursor=LoadImage("cursor.png")
MaskImage cursor,0,17,119

HidePointer

guilayer=LoadImage("gui.bmp")

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)

;Checking for mouseclicks
buttonpress=0 ; Cancel all button presses first
If MouseDown(1)

If MouseX()>256 And MouseX()<278 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=7 : vholdspeed#=vholdspeed#-1 : mouseclick=1
If MouseX()>282 And MouseX()<304 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=8 : vholdspeed#=vholdspeed#+1 : mouseclick=1

Else
	mouseclick=0
EndIf

	;Looping the vholdspeed# variable
	If vholdspeed# > 10
		vholdspeed# = -8
	ElseIf vholdspeed# < -8
		vholdspeed# = 10
	EndIf

	If vholdspeed# <> 0
		vholdpos#=vholdpos#+vholdspeed#
	Else
		If vholdpos# <> 0
			If vholdpos# >= 70 Then vholdpos#=vholdpos#+0.5 Else vholdpos#=vholdpos#-0.5
			
		EndIf
	EndIf

	;Change the vholdpos variable
	
	If vholdpos# >= 140+bandheight
		vholdpos#=0
	ElseIf vholdpos# <= 0
		vholdpos#=140+bandheight
	EndIf
	
	;Copy the portion of the backbuffer that has the picture in it to the image
	GrabImage image,77,19
	
	;Apply whatever effects are required to the image
	SetBuffer ImageBuffer(image)
		
	Color 0,0,0
	Rect 49,120,114,20
	
	For i=1 To 140 Step 2 : Rect 0,i,212,1 : Next ;Scanlines
	
	Color 255,255,255
	Text 85,125,"00:00"

	SetBuffer BackBuffer()
	
	;Clear the screen of the editing
	Cls
			
	;Draw the 'effects' image over the 'real' camera image, adding vhold effect
	DrawBlockRect image,77,19,0,vholdpos#,212,140-vholdpos#
	;Vhold Band
	;Color 0,0,0
	;Rect 77,19+(140-vholdpos#),212,140-(140-vholdpos#)
	;Color 255,255,255
	;Second Half of image
	DrawBlockRect image,77,19+(140-vholdpos#)+bandheight,0,0,212,140-(140-vholdpos#)-bandheight
	
	

	DrawImage guilayer,0,0
	
	DrawImage cursor,MouseX()-7,MouseY()-2 ;Draw the cursor
	
	Text 0,0,vholdpos#
	Text 0,10,vholdspeed#
	
	
	Flip
	
	UpdateWorld
	RenderWorld
 
Wend

End