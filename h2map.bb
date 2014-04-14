Graphics3D 640,480,32,2
SetBuffer BackBuffer()

camera=CreateCamera()
light=CreateLight()

RotateEntity camera,45,0,0

; camera position values (ignore)
cx#=4
cy#=mapy#+6.5
cz#=-11

PositionEntity camera,cx#,cy#,cz#

;for the mapcamera, the y value should be mapy#+6.5, and rotation
RotateEntity camera,90,0,0

;Creating the building
Include "levelbuild.bb"

;Loading Textures for map
map=LoadTexture("map.png")
mrubmp=LoadTexture("mru.png")
annbmp=LoadTexture("ann.png")
guardbmp=LoadTexture("guard.png")
cambmp=LoadTexture("cam.png")

;TGS map
mapy#=0.01
tgsmap=CreateFloor(0,0,38,25)
EntityTexture tgsmap,map
PositionEntity tgsmap,0.5,mapy#,-0.5
;HideEntity tgsmap
EntityAlpha tgsmap,0.7

;Temporary Guard object
guard=CreateCube()
EntityColor guard,255,255,0
guardx#=4
guardz#=-4
ScaleEntity guard,0.5,1,0.5
PositionEntity guard,guardx#,1,guardz#

;Temporary MRU object
mru=CreateCube()
EntityColor mru,0,255,0
mrux#=2
mruz#=-11
mruy#=0.26
ScaleEntity mru,0.2,0.26,0.3
PositionEntity mru,mrux#,mruy#,mruz#
mrudirection=1
mrumovez=0
mrumovex=0
mrumove#=1
mrumovespeed#=0.01
mruturnspeed#=1

;HideEntity mru

mruperfromaction=0

;Temporary Annihilator object
ann=CreateCube()
EntityColor ann,255,0,0
annx#=16
annz#=-4
ScaleEntity ann,0.5,1,0.5
PositionEntity ann,annx#,1,annz#
annmovespeed#=0.013
EntityAlpha ann,0.5

;Map Objects
mapmru=CreateFloor(0,0,1,1)
EntityTexture mapmru,mrubmp
mapann=CreateFloor(0,0,1,1)
EntityTexture mapann,annbmp
mapguard=CreateFloor(0,0,1,1)
EntityTexture mapguard,guardbmp
mapcama=CreateFloor(0,0,0.99,1.5)
EntityTexture mapcama,cambmp
mapcamb=CreateFloor(0,0,0.99,1.5)
EntityTexture mapcamb,cambmp

;Hide the annilihator
HideEntity ann
HideEntity mapann

;Variables
timecheck=MilliSecs()
secs=0
mins=0
completesecs=0
fakemins=0
milli=0
secss$="00"
minss$="00"
millicount=MilliSecs()
secmonb=31

;Array, 38 by 25
Dim walkability(38,25)

;Loading images into array
bmp=LoadImage("bitmap.bmp")
For i=1 To 38
	For v=1 To 25
		walkability(i,v)=ReadPixel(i-1,v-1,ImageBuffer(bmp))
	Next
Next
FreeImage bmp

;Include external bb files
Include "camarray.bb"
Include "aStarLibrary.bb"

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)


If stopclock=0
;Update the clock
If MilliSecs() > millicount + 10
	If milli >=59
		milli=0
		If secs >= 59
			secs=0
			completesecs=completesecs+1
			If fakemins >= 59
				secs=0
				completesecs=completesecs+1
				fakemins=0
				mins=0
			Else
				fakemins=fakemins+1
				If mins >= 4 : mins=0 : Else : mins=mins+1 : EndIf
			EndIf
		Else
			secs=secs+1
			completesecs=completesecs+1
		EndIf
	Else
		milli=milli+1
	EndIf
	If secs < 10 : secss$="0"+secs : Else : secss$=secs : EndIf
	If fakemins < 10 : minss$="0"+fakemins : Else : minss$=fakemins : EndIf
	millicount=MilliSecs()
EndIf
EndIf ;If stopclock=0

;Calculate which cameras are being monitored
If secs<2 : secmona=30 : Else : secmona=Int(secs/2) : EndIf
If twoseconds+1<completesecs
	If secmonb>37 : secmonb=31 : Else : secmonb=secmonb+1 : EndIf
	twoseconds=completesecs
EndIf

;Update the guard's position
;Given a time, (mins*3600)+(secs#60) calculates either an x or a z coord for the guard
guardpos#=(mins*3600)+(secs*60)+milli

If guardpos# <= 1080
	guardz# = -4-(guardpos#/120) : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 2280
	guardx# = (guardpos#/120)-5 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 2880
	guardz# = 6-(guardpos#/120) : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 4080
	guardx# = 38-(guardpos#/120) : gdir$="X" : guarddir=-1
ElseIf guardpos# <= 4440
	guardz# = 16-(guardpos#/120) : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 5640
	guardx# = (guardpos#/120)-33 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 7680
	guardz# = (guardpos#/120)-68 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 8160
	guardx# = -50+(guardpos#/120) : gdir$="X" : guarddir=1
ElseIf guardpos# <= 10200
	guardz# = 64-(guardpos#/120) : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 11400
	guardx# = (guardpos#/120)-67 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 11760
	guardz# = (guardpos#/120)-116 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 12600
	guardx# = (guardpos#/120)-70 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 13200
	guardz# = (guardpos#/120)-123 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 14040
	guardx# = 145-(guardpos#/120) : gdir$="X" : guarddir=-1
ElseIf guardpos# <= 15120
	guardz# = (guardpos#/120)-130 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 18000
	guardx# = 154-(guardpos#/120) : gdir$="X" : guarddir=-1
EndIf

PositionEntity guard,guardx#,1,guardz#

;camera rubbish
; Change position values depending on key pressed
If KeyDown( 203 )=True Then cx#=cx#-0.1
If KeyDown( 205 )=True Then cx#=cx#+0.1
If KeyDown( 208 )=True Then cy#=cy#-0.1
If KeyDown( 200 )=True Then cy#=cy#+0.1
If KeyDown( 44 )=True Then cz#=cz#-0.1
If KeyDown( 30 )=True Then cz#=cz#+0.1
PositionEntity camera,cx#,cy#,cz#

;Camera movement for the map camera
If Int(EntityX#(mru))>=4 And Int(EntityX#(mru))<=35 And Int(EntityZ#(mru))<=-4 And Int(EntityZ#(mru))>=-22
	PositionEntity camera,Int(EntityX#(mru)),mapy#+6.5,Int(EntityZ#(mru))
EndIf



If KeyDown( 57 )=True Then mruspotted=0

If nocontrolmru=0 ;If MRU movement is allowed

;MRU keymovement
If KeyDown( 23 )=True ;Forwards
	If keypress1=0 And mruperformaction=0
			If mrumovez=0 And mrudirection=1 ; North
				posneg=1
				Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=2 ; East
				posneg=1
				Gosub moveX
			EndIf
			If mrumovez=0 And mrudirection=3 ; South
				posneg=-1
				Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=4 ; West
				posneg=-1
				Gosub moveX
			EndIf
		keypress1=1
	EndIf
Else
	keypress1=0
EndIf

If KeyDown( 37 )=True ;Backwards
	If keypress2=0 And mruperformaction=0
			If mrumovez=0 And mrudirection=1 ; North
				posneg=-1
				Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=2 ; East
				posneg=-1
				Gosub moveX
			EndIf
			If mrumovez=0 And mrudirection=3 ; South
				posneg=1
				Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=4 ; West
				posneg=1
				Gosub moveX
			EndIf
		keypress2=1
	EndIf
Else
	keypress2=0
EndIf

If KeyDown( 36 )=True ; Turning to the Left
	If keypress3=0 And mruperformaction=0
			If mruturnl=0
				mrudirection=mrudirection-1
				mruturntarget#=90
				turntarget#=EntityYaw#(mru)+90
				mruturnl=1
			EndIf
		keypress3=1
	EndIf
Else
	keypress3=0
EndIf
If KeyDown( 38 )=True ; Turning to the Right
	If keypress4=0 And mruperformaction=0
			If mruturnr=0
				mrudirection=mrudirection+1
				mruturntarget#=-90
				turntarget#=EntityYaw#(mru)-90
				mruturnr=1
			EndIf
		keypress4=1
	EndIf
Else
	keypress4=0
EndIf

EndIf ;If nocontrolmru=0

If mrudirection<1 Then mrudirection=4
If mrudirection>4 Then mrudirection=1

;Moving the MRU
If mrumovez=1 ;In the Z axis
	If mrutargetz#>0
		mruperformaction=1
		mrutargetz#=mrutargetz#-mrumovespeed#
	 	PositionEntity mru,mrux#,mruy#,EntityZ#(mru)+(posneg*mrumovespeed#)
	Else
		mruperformaction=0
		PositionEntity mru,mrux#,mruy#,mrutarget#
		mrumovez=0
		keypress1=0
		keypress2=0
	EndIf
EndIf
If mrumovex=1 ;In the X axis
	If mrutargetx#>0
		mruperformaction=1
		mrutargetx#=mrutargetx#-mrumovespeed#
	 	PositionEntity mru,EntityX#(mru)+(posneg*mrumovespeed#),mruy#,mruz#
	Else
		mruperformaction=0
		PositionEntity mru,mrutarget#,mruy#,mruz#
		mrumovex=0
		keypress1=0
		keypress2=0
	EndIf
EndIf

;Turning the MRU
If mruturnl=1 ; To the left
	If mruturntarget#>0
		mruperformaction=1
		mruturntarget#=mruturntarget#-mruturnspeed#
		RotateEntity mru,0,EntityYaw#(mru)+mruturnspeed#,0
	Else
		mruperformaction=0
		RotateEntity mru,0,turntarget#,0
		RotateTexture mrubmp,turntarget#
		mruturnl=0
		keypress3=0
	EndIf
EndIf
If mruturnr=1 ; To the right
	If mruturntarget#<0
		mruperformaction=1
		mruturntarget#=mruturntarget#+mruturnspeed#
		RotateEntity mru,0,EntityYaw#(mru)-mruturnspeed#,0
	Else
		mruperformaction=0
		RotateEntity mru,0,turntarget#,0
		RotateTexture mrubmp,turntarget#
		mruturnr=0
		keypress4=0
	EndIf
EndIf

;Update the mru positions
mrux#=EntityX#(mru)
mruz#=EntityZ#(mru)

If guardvision=0 ;If Guard vision checks are needed
;Check whether or not the guard can see the MRU (don't do if the guard has already seen the MRU)
;Calculate 'rays' from guard to walls using bitmapv
guardbmpx=Int(EntityX#(guard))
guardbmpz=Int(EntityZ#(guard))
guardbmpposz=guardbmpz
guardbmpposz2=guardbmpz
guardbmpposxl=guardbmpx
guardbmpposxr=guardbmpx
;If the guard is moving in the Z Axis
If gdir$="Z"
	;First, one ray straight ahead, in the direction the guard is facing
	Repeat
		guardbmpposz=guardbmpposz+guarddir
	Until walkability(guardbmpx,guardbmpposz*-1) <> -1
	;Next, one ray to one side (-1)
	Repeat
		guardbmpposxl=guardbmpposxl-1
	Until walkability(guardbmpposxl,guardbmpz*-1) <> -1
	;Last, one ray to the other side (+1)
	Repeat
		guardbmpposxr=guardbmpposxr+1
	Until walkability(guardbmpposxr,guardbmpz*-1) <> -1
	;Find the total distance between the side rays
	sidedist=(guardbmpposxr-Int(EntityX#(guard))) - (guardbmpposxl-Int(EntityX#(guard)))
	;Check each square ahead to see if the the mru matches (*3)
	For gx=-1 To 1
	For gz=1 To (guardbmpposz-Int(EntityZ#(guard)))*guarddir ;guardbmpposz-(Int(EntityZ#(guard))*guarddir)
		;If Int(EntityZ#(mru)) = Int(EntityZ#(guard))-(gz*guarddir) And Int(EntityX#(mru)) = Int(EntityX#(guard))+gx
		If Int(EntityZ#(mru)) = Int(EntityZ#(guard))+(gz*guarddir) And Int(EntityX#(mru)) = Int(EntityX#(guard))+gx
			mruspotted=1 : whosawmru=1
		EndIf
	Next
	Next
	;Check each square to the side to see if the the mru matches
	For gz=1 To sidedist-1
		If Int(EntityX#(mru)) = Int(EntityX#(guard))+guardbmpposxl-Int(EntityX#(guard))+gz And Int(EntityZ#(mru)) = Int(EntityZ#(guard))
			mruspotted=1 : whosawmru=1
		EndIf
	Next
	
;And a similar set of checks and operations if guard is moving in the X axis
ElseIf gdir$="X"
	;First, one ray straight ahead, in the direction the guard is facing
	Repeat
		guardbmpposxl=guardbmpposxl+guarddir
	Until walkability(guardbmpposxl,guardbmpz*-1) <> -1
	;Next, one ray to one side (-1)
	Repeat
		guardbmpposz=guardbmpposz-1
	Until walkability(guardbmpx,guardbmpposz*-1) <> -1
	;Last, one ray to the other side (+1)
	Repeat
		guardbmpposz2=guardbmpposz2+1
	Until walkability(guardbmpx,guardbmpposz2*-1) <> -1
	;Find the total distance between the side rays
	sidedist=(guardbmpposz2-Int(EntityZ#(guard))) - (guardbmpposz-Int(EntityZ#(guard)))
	;Check each square ahead to see if the the mru matches (*3)
	For gx=-1 To 1
	For gz=1 To (guardbmpposxl-Int(EntityX#(guard)))*guarddir ;guardbmpposxl-(Int(EntityX#(guard))*guarddir)
		If Int(EntityX#(mru)) = Int(EntityX#(guard))+(gz*guarddir) And Int(EntityZ#(mru)) = Int(EntityZ#(guard))+gx
			mruspotted=1 : whosawmru=1
		EndIf
	Next
	Next
	;Check each square to the side to see if the the mru matches
	For gz=1 To sidedist-1
		If Int(EntityZ#(mru)) = Int(EntityZ#(guard))+guardbmpposz-Int(EntityZ#(guard))+gz And Int(EntityX#(mru)) = Int(EntityX#(guard))
			mruspotted=1 : whosawmru=1
		EndIf
	Next
EndIf
EndIf ;If guard vision checks are needed

;Security Cameras
;Only two can be 'active' at once - one for Secutity monitor A and one of monitor B.
;The camera array holds data for which coords each camera can see. Use this to determine if the MRU can be seen.
If camarray(secmona,2) = 1 And secmona <> 28
	If Int(EntityX#(mru))=camarray(secmona,0)-1 And Int(EntityZ#(mru))=camarray(secmona,1)+1 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+1 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0)+1 And Int(EntityZ#(mru))=camarray(secmona,1)+1 Then mruspotted=1 : whosawmru=2
ElseIf camarray(secmona,2) = 2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+1 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0)-1 And Int(EntityZ#(mru))=camarray(secmona,1)+2 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+2 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0)+1 And Int(EntityZ#(mru))=camarray(secmona,1)+2 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+3 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+4 Then mruspotted=1 : whosawmru=2
ElseIf camarray(secmona,2) = 3
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+1 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+2 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0)-1 And Int(EntityZ#(mru))=camarray(secmona,1)+3 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+3 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0)+1 And Int(EntityZ#(mru))=camarray(secmona,1)+3 Then mruspotted=1 : whosawmru=2
	If Int(EntityX#(mru))=camarray(secmona,0) And Int(EntityZ#(mru))=camarray(secmona,1)+4 Then mruspotted=1 : whosawmru=2
EndIf
;Do the same for secmonb, though they can only ever be type 1
If Int(EntityX#(mru))=camarray(secmonb,0)-1 And Int(EntityZ#(mru))=camarray(secmonb,1)+1 Then mruspotted=1 : whosawmru=2
If Int(EntityX#(mru))=camarray(secmonb,0) And Int(EntityZ#(mru))=camarray(secmonb,1)+1 Then mruspotted=1 : whosawmru=2
If Int(EntityX#(mru))=camarray(secmonb,0)+1 And Int(EntityZ#(mru))=camarray(secmonb,1)+1 Then mruspotted=1 : whosawmru=2


If mruspotted=1
;Only set off alarm once MRU has reached it's target.
	If mrumovez=0 And mrumovex=0
		;Stop the clock
		stopclock=1
		;Hide the guard
		HideEntity guard
		HideEntity mapguard
		;Show the annihilator
		ShowEntity ann
		ShowEntity mapann
		;Disable the 'Guard checking for sight of MRU' routine (above)
		guardvision=1
		;Also disable the cameras
		If whosawmru=1 ;Display whatever message is appropriate
			;'Guard has seen the MRU'
		ElseIf whosawmru=2
			;'Security cameras have seen the MRU'
		EndIf
		;Set the annihilator off
		anngo=1
	EndIf
EndIf


If anngo=1
;Move the annihalator
	If EntityX#(mru) <> EntityX#(ann) Or EntityZ#(mru) <> EntityZ#(ann)
		If pathStatus(1) = notstarted Or pathlocation(1) = 3
			PathStatus(1) = findPath(1,annx#,annz#*-1,EntityX#(mru),EntityZ#(mru)*-1)
		End If
		readPath(1,EntityX#(ann),EntityZ#(ann)*-1,annmovespeed#)
	
		If annx# > xPath(1) Then annx# = annx# - annmovespeed#
		If annx# < xPath(1) Then annx# = annx# + annmovespeed#
		If annz#*-1 > yPath(1) Then annz# = annz# + annmovespeed#
		If annz#*-1 < yPath(1) Then annz# = annz# - annmovespeed#
	EndIf
	
	If Int(EntityX#(mru))=Int(EntityX(ann))+1 Or Int(EntityX#(mru))=Int(EntityX(ann))-1 And Int(EntityZ#(mru)) = Int(EntityZ#(ann))
		;Stop and take control away from mru
		Gosub StopMRU
	ElseIf Int(EntityZ#(mru))=Int(EntityZ(ann))+1 Or Int(EntityZ#(mru))=Int(EntityZ(ann))-1 And Int(EntityX#(mru)) = Int(EntityX#(ann))
		Gosub StopMRU
	EndIf
	
	If nocontrolmru=1 And MilliSecs() > anntimecount + 2000 ;if the mru has been stopped, and it's been (time) since it stopped
			Gosub mrucaught
	EndIf

EndIf

PositionEntity ann,annx#,1,annz#

;Positioning the map objects
PositionEntity mapmru,Int(EntityX#(mru))-0.49,mapy#+0.001,Int(EntityZ#(mru))+0.485
PositionEntity mapann,Int(EntityX#(ann))-0.49,mapy#+0.0011,Int(EntityZ#(ann))+0.485
PositionEntity mapguard,Int(EntityX#(guard))-0.49,mapy#+0.0005,Int(EntityZ#(guard))+0.485
PositionEntity mapcama,camarray(secmona,0)-0.478,mapy#+0.0012,camarray(secmona,1)+0.8
PositionEntity mapcamb,camarray(secmonb,0)-0.478,mapy#+0.0012,camarray(secmonb,1)+0.8

UpdateWorld
RenderWorld

Text 0,0,mrumovez
Text 0,10,mrumovex
Text 0,20,cz#

Text 0,40,"Clock: "+minss$+" : "+secss$
Text 0,50,"Real Time: "+mins+" : "+secs+" : "+milli

Text 0,70,"MRU X: "+Int(EntityX#(mru))
Text 0,80,"MRU Z: "+Int(EntityZ#(mru))
Text 0,90,"ANN X: "+EntityX#(ann)
Text 0,100,"ANN Z: "+EntityZ#(ann)

Text 0,110,"SECURITY MON A: "+secmona
Text 0,120,"SECURITY MON B: "+secmonb

Text 0,130,"MRU SPOTTED= "+mruspotted
Text 0,140,(guardbmpposz-Int(EntityZ#(guard)))*guarddir
Text 0,150,(guardbmpposxl-Int(EntityX#(guard)))*guarddir

Text 0,170,TrisRendered() 



Flip

Wend

End

;SUBROUTINES
;------------
.moveX
	mrutarget#=EntityX#(mru)+(mrumove#*posneg)
	mrutargetx#=mrumove#
	mrumovex=1
	Gosub collisioncheckx
Return

.moveZ
	mrutarget#=EntityZ#(mru)+(mrumove#*posneg)
	mrutargetz#=mrumove#
	mrumovez=1
	Gosub collisioncheckz
Return

.collisioncheckx
	If walkability(mrutarget#,EntityZ#(mru)*-1) = -16777216
		mrutarget#=EntityX#(mru)
		mrutargetx#=0
		mrumovex=0
	EndIf
Return

.collisioncheckz
	If walkability(EntityX#(mru),mrutarget#*-1) = -16777216
		mrutarget#=EntityZ#(mru)
		mrutargetz#=0
		mrumovez=0
	EndIf
Return

.mrucaught
	mrucaught=1
	anngo=0
	;Destroy the mru
	HideEntity mru
	;Display message 'MRU destroyed, you have X remaining'
	;Reset mru position, make sure mru control and guard vision are enabled, etc...
	;doonce=0
Return

.stopMRU
	If doonce=0
		nocontrolmru=1
		anntimecount=MilliSecs()
		doonce=1
	EndIf
Return

;FUNCTIONS
;------------
;Floor Pieces:
;------------
Function CreateFloor(xpos#,zpos#,x#,z#)
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,xpos#,0,zpos#,      0,0)
AddVertex ( v,xpos#+x#,0,zpos#,   1,0)
AddVertex ( v,xpos#+x#,0,zpos#-z#,1,1)
AddVertex ( v,xpos#,0,zpos#-z#,   0,1)
AddTriangle( v,0,1,2)
AddTriangle( v,2,3,0)
Return sprite
End Function

Function CreateFront(xpos#,zpos#,x#)
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,xpos#,1,zpos#,     0,0)
AddVertex ( v,xpos#+x#,1,zpos#,  1,0)
AddVertex ( v,xpos#+x#,0,zpos#,  1,1)
AddVertex ( v,xpos#,0,zpos#,     0,1)
AddTriangle( v,0,1,2)
AddTriangle( v,2,3,0)
Return sprite
End Function

Function CreateDoor(xpos#,zpos#,x#,z#,doorstart#,doorwidth#,doorvar); For creating the walls with doors in them (Z axis)
wallheight#=1
doorheight#=0.6
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
If doorvar=1 ;If an open door is wanted
	;One side of the wall
	AddVertex ( v,xpos#,wallheight#,zpos#-z#,					0,0) ;0
	AddVertex ( v,xpos#,wallheight#,zpos#,						1,0) ;1
	AddVertex ( v,xpos#,0,zpos#,								1,1) ;2
	AddVertex ( v,xpos#,0,zpos#-doorstart#,						0.67,1) ;3
	AddVertex ( v,xpos#,doorheight#,zpos#-doorstart#,			0.67,0.25) ;4
	AddVertex ( v,xpos#,doorheight#,zpos#-doorstart#-doorwidth#,0.33,0.25) ;5
	AddVertex ( v,xpos#,0,zpos#-doorstart#-doorwidth#,			0.33,1) ;6
	AddVertex ( v,xpos#,0,zpos#-z#,								0,1) ;7
	;Other side of the wall
	AddVertex ( v,xpos#+x#,wallheight#,zpos#-z#,					0,0) ;8
	AddVertex ( v,xpos#+x#,wallheight#,zpos#,						1,0) ;9
	AddVertex ( v,xpos#+x#,0,zpos#,									1,1) ;10
	AddVertex ( v,xpos#+x#,0,zpos#-doorstart#,						0.67,1) ;11
	AddVertex ( v,xpos#+x#,doorheight#,zpos#-doorstart#,			0.67,0.25) ;12
	AddVertex ( v,xpos#+x#,doorheight#,zpos#-doorstart#-doorwidth#,	0.33,0.25) ;13
	AddVertex ( v,xpos#+x#,0,zpos#-doorstart#-doorwidth#,			0.33,1) ;14
	AddVertex ( v,xpos#+x#,0,zpos#-z#,								0,1) ;15
	;Build left side of wall
	AddTriangle( v,0,4,1)
	AddTriangle( v,1,4,2)
	AddTriangle( v,2,4,3)
	AddTriangle( v,4,0,5)
	AddTriangle( v,5,0,7)
	AddTriangle( v,7,6,5)
	;Build right side of wall
	AddTriangle( v,8,9,12)
	AddTriangle( v,12,9,10)
	AddTriangle( v,10,11,12)
	AddTriangle( v,12,13,8)
	AddTriangle( v,8,13,15)
	AddTriangle( v,15,13,14)
	;build side of inner doorframe
	AddTriangle( v,4,12,11)
	AddTriangle( v,11,3,4)
	;build top of inner doorframe
	AddTriangle( v,5,13,12)
	AddTriangle( v,12,4,5)
ElseIf doorvar=0 ;Otherwise, build a plain wall with no door
	;One side of the wall
	AddVertex ( v,xpos#,wallheight#,zpos#-z#,	0,0) ;0
	AddVertex ( v,xpos#,wallheight#,zpos#,		1,0) ;1
	AddVertex ( v,xpos#,0,zpos#,				1,1) ;2
	AddVertex ( v,xpos#,0,zpos#-z#,				0,1) ;3
	;Other side of the wall
	AddVertex ( v,xpos#+x#,wallheight#,zpos#-z#,0,0) ;4
	AddVertex ( v,xpos#+x#,wallheight#,zpos#,	1,0) ;5
	AddVertex ( v,xpos#+x#,0,zpos#,				1,1) ;6
	AddVertex ( v,xpos#+x#,0,zpos#-z#,			0,1) ;7
	;Build left side of wall
	AddTriangle( v,0,2,1)
	AddTriangle( v,0,3,2)
	;Build right side of wall
	AddTriangle( v,4,5,6)
	AddTriangle( v,4,6,7)
EndIf

Return sprite

End Function