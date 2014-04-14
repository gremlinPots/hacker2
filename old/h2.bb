Graphics3D 640,480
SetBuffer BackBuffer()

camera=CreateCamera()
light=CreateLight()

RotateEntity camera,90,0,0

; camera position values (ignore)
cx#=32.8
cy#=34.6
cz#=23.3

;Creating the building
;Horizontals
CreateFloor(0,0,23,3)
CreateFloor(3,6,17,3)
CreateFloor(3,16,17,3)
CreateFloor(0,34,65,3)
CreateFloor(28,0,37,3)
CreateFloor(51,6,11,3)
CreateFloor(51,16,11,3)

;Verticals
CreateFloor(0,3,3,31)
CreateFloor(20,3,3,31)
CreateFloor(28,3,3,31)
CreateFloor(48,3,3,31)
CreateFloor(62,3,3,31)

;Temporary Guard object
guard=CreateCube()
EntityColor guard,255,0,0
guardx#=1.5
guardz#=35.5
PositionEntity guard,guardx#,1,guardz#

;Temporary MRU object
mru=CreateCube()
EntityColor mru,0,0,255
mrux#=1.5
mruz#=35.5
ScaleEntity mru,0.8,1,0.5
PositionEntity mru,mrux#,1,mruz#
mrudirection=3
mrumovez=0
mrumovex=0
mrumove#=2
mrumovespeed#=0.02
mruturnspeed#=1

mruperfromaction=0

;Variables
timecheck=MilliSecs()
secs=0
mins=0
fakemins=0
milli=0
secss$="00"
minss$="00"
millicount=MilliSecs()

;Array, 25 by 38
Dim bitmap(24,37)

;Loading images into array
bmp=LoadImage("bitmap.bmp")
For i=0 To 24
	For v=0 To 37
		bitmap(i,v)=ReadPixel(i,v,ImageBuffer(bmp))
	Next
Next

;Include "aStarLibrary.bb"

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)

;Update the clock
If MilliSecs() > millicount + 10
	If milli >=59
		milli=0
		If secs >= 59
			secs=0
			If fakemins >= 59
				secs=0
				fakemins=0
				mins=0
			Else
				fakemins=fakemins+1
				If mins >= 4 : mins=0 : Else : mins=mins+1 : EndIf
			EndIf
		Else
			secs=secs+1
		EndIf
	Else
		milli=milli+1
	EndIf
	If secs < 10 : secss$="0"+secs : Else : secss$=secs : EndIf
	If fakemins < 10 : minss$="0"+fakemins : Else : minss$=fakemins : EndIf
	millicount=MilliSecs()
EndIf

;Update the guard's position
;Given a time, (mins*3600)+(secs#60) calculates either an x or a z coord for the guard

guardpos#=(mins*3600)+(secs*60)+milli

If guardpos# <= 1080
	guardz# = 35.5-(guardpos#/60) : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 2280
	guardx# = (1.5+(guardpos#/60))-18 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 2880
	guardz# = (21.5-(guardpos#/60))+34 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 4080
	guardx# = (21.5-(guardpos#/60))+48 : gdir$="X" : guarddir=-1
ElseIf guardpos# <= 4440
	guardz# = (7.5-(guardpos#/60))+68 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 5640
	guardx# = (1.5+(guardpos#/60))-74 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 7680
	guardz# = (21.5+(guardpos#/60))-114 : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 8160
	guardx# = (21.5+(guardpos#/60))-128 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 10200
	guardz# = (35.5-(guardpos#/60))+136 : gdir$="Z" : guarddir=1
ElseIf guardpos# <= 11400
	guardx# = (29.5+(guardpos#/60))-170 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 11760
	guardz# = (1.5+(guardpos#/60))-190 : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 12600
	guardx# = (49.5+(guardpos#/60))-196 : gdir$="X" : guarddir=1
ElseIf guardpos# <= 13200
	guardz# = (7.5+(guardpos#/60))-210 : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 14040
	guardx# = (63.5-(guardpos#/60))+220 : gdir$="X" : guarddir=-1
ElseIf guardpos# <= 15120
	guardz# = (17.5+(guardpos#/60))-234 : gdir$="Z" : guarddir=-1
ElseIf guardpos# <= 18000
	guardx# = (29.5-(guardpos#/60))+272 : gdir$="X" : guarddir=-1
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

If KeyDown( 57 )=True Then guardseemru=0

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

If mrudirection<1 Then mrudirection=4
If mrudirection>4 Then mrudirection=1

;Moving the MRU
If mrumovez=1 ;In the Z axis
	If mrutargetz#>0
		mruperformaction=1
		mrutargetz#=mrutargetz#-mrumovespeed#
	 	PositionEntity mru,mrux#,1,EntityZ#(mru)+(posneg*mrumovespeed#)
	Else
		mruperformaction=0
		PositionEntity mru,mrux#,1,mrutarget#
		mrumovez=0
		keypress1=0
		keypress2=0
	EndIf
EndIf
If mrumovex=1 ;In the X axis
	If mrutargetx#>0
		mruperformaction=1
		mrutargetx#=mrutargetx#-mrumovespeed#
	 	PositionEntity mru,EntityX#(mru)+(posneg*mrumovespeed#),1,mruz#
	Else
		mruperformaction=0
		PositionEntity mru,mrutarget#,1,mruz#
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
		mruturnr=0
		keypress4=0
	EndIf
EndIf

;Update the mru positions
mrux#=EntityX#(mru)
mruz#=EntityZ#(mru)

;Check whether or not the guard can see the MRU
;Calculate 'rays' from guard to walls using bitmapv
guardbmpz=Int(EntityZ#(guard)-41.5)/-2
guardbmpx=Int(EntityX#(guard)+4.5)/2
guardbmpposz=guardbmpz
guardbmpposz2=guardbmpz
guardbmpposxl=guardbmpx
guardbmpposxr=guardbmpx
;If the guard is moving in the Z Axis
If gdir$="Z"
	;First, one ray straight ahead, in the direction the guard is facing
	Repeat
		guardbmpposz=guardbmpposz+guarddir
	Until bitmap(guardbmpposz,guardbmpx) <> -1
	;Next, one ray to one side (-1)
	Repeat
		guardbmpposxl=guardbmpposxl-1
	Until bitmap(guardbmpz,guardbmpposxl) <> -1
	;Last, one ray to the other side (+1)
	Repeat
		guardbmpposxr=guardbmpposxr+1
	Until bitmap(guardbmpz,guardbmpposxr) <> -1
	;Find the total distance between the side rays
	sidedist=((guardbmpposxr-Int(EntityX#(guard)+4.5)/2)-((guardbmpposxl-Int(EntityX#(guard)+4.5)/2)))
	;Check each square ahead to see if the the mru matches (*3)
	For gx=-1 To 1
	For gz=1 To (guardbmpposz-Int(EntityZ#(guard)-41.5)/-2)*guarddir
		If Int(EntityZ#(mru)-41.5)/-2 = Int((EntityZ#(guard)-41.5)/-2)+(gz*guarddir) And Int(EntityX#(mru)+4.5)/2 = Int((EntityX#(guard)+4.5)/2)+gx
			guardseemru=1
		EndIf
	Next
	Next
	;Check each square to the side to see if the the mru matches
	For gz=1 To sidedist
		If Int(EntityX#(mru)+4.5)/2 = Int((EntityX#(guard)+4.5)/2)+((guardbmpposxl-Int(EntityX#(guard)+4.5)/2))+gz And Int(EntityZ#(mru)-41.5)/-2 = Int((EntityZ#(guard)-41.5)/-2)
			guardseemru=1
		EndIf
	Next
;And a similar set of checks and operations if guard is moving in the X axis
ElseIf gdir$="X"
	;First, one ray straight ahead, in the direction the guard is facing
	Repeat
		guardbmpposxl=guardbmpposxl+guarddir
	Until bitmap(guardbmpz,guardbmpposxl) <> -1
	;Next, one ray to one side (-1)
	Repeat
		guardbmpposz=guardbmpposz-1
	Until bitmap(guardbmpposz,guardbmpx) <> -1
	;Last, one ray to the other side (+1)
	Repeat
		guardbmpposz2=guardbmpposz2+1
	Until bitmap(guardbmpposz2,guardbmpx) <> -1
	;Find the total distance between the side rays
	sidedist=((guardbmpposz2-Int(EntityZ#(guard)-41.5)/-2))-((guardbmpposz-Int(EntityZ#(guard)-41.5)/-2))
	;Check each square ahead to see if the the mru matches (*3)
	For gx=-1 To 1
	For gz=1 To (guardbmpposxl-Int(EntityX#(guard)+4.5)/2)*guarddir
		If Int(EntityX#(mru)+4.5)/2 = Int((EntityX#(guard)+4.5)/2)+(gz*guarddir) And Int(EntityZ#(mru)-41.5)/-2 = Int((EntityZ#(guard)-41.5)/-2)+gx
			guardseemru=1
		EndIf
	Next
	Next
	;Check each square to the side to see if the the mru matches
	For gz=1 To sidedist
		If Int(EntityZ#(mru)-41.5)/-2 = Int((EntityZ#(guard)-41.5)/-2)+((guardbmpposz-Int(EntityZ#(guard)-41.5)/-2))+gz And Int(EntityX#(mru)+4.5)/2 = Int((EntityX#(guard)+4.5)/2)
			guardseemru=1
		EndIf
	Next
EndIf

If guardseemru=1
;Only set off alarm once MRU has reached it's target.
	If mrumovez=0 And mrumovex=0
		;guardseemru=0
		;targetx#=EntityX#(mru)
		;targety#=EntityZ#(mru)
		;startingx#=25.5
		;startingy#=35.5
		;pathresult=findPath(1,startingX#,startingY#,targetX#,targetY#)
		;anngo=1
	EndIf
EndIf

UpdateWorld
RenderWorld

Text 0,0,mruperformaction
Text 0,10,keypress2
Text 0,20,keypress3

Text 0,40,"Clock: "+minss$+" : "+secss$
Text 0,50,"Real Time: "+mins+" : "+secs+" : "+milli

Text 0,70,"MRU X: "+EntityX#(mru)
Text 0,80,"MRU Z: "+EntityZ#(mru)
Text 0,90,"MRU TAR X: "+mrutargetx#
Text 0,100,"MRU TAR Z: "+mrutargetz#

Text 0,120,"GUARD CAN SEE MRU= "+guardseemru
Text 0,130,"GUARD BMP POS X= "+Int(EntityZ#(guard)-41.5)/-2
Text 0,140,"GUARD BMP POS Z= "+Int(EntityX#(guard)+4.5)/2
Text 0,150,(guardbmpposz-Int(EntityZ#(guard)-41.5)/-2)*guarddir
;Text 0,160,pathresult
;Text 0,170,pathlength(1)




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
	If bitmap((EntityZ#(mru)-41.5)/-2,(mrutarget#+4.5)/2) = -16777216
		mrutarget#=EntityX#(mru)
		mrutargetx#=0
		mrumovex=0
	EndIf
Return

.collisioncheckz
	If bitmap((mrutarget#-41.5)/-2,(EntityX#(mru)+4.5)/2) = -16777216
		mrutarget#=EntityZ#(mru)
		mrutargetz#=0
		mrumovez=0
	EndIf
Return

;FUNCTIONS
;------------
;Floor Pieces:
;------------
Function CreateFloor(xpos,zpos,x,z)
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,xpos,0,zpos+z,1,1)
AddVertex ( v,xpos+x,0,zpos+z,0,1)
AddVertex ( v,xpos,0,zpos,1,0)
AddVertex ( v,xpos+x,0,zpos,0,0)
AddTriangle( v,0,1,2)
AddTriangle( v,3,2,1)
Return sprite
End Function

Function CreateFront()
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,0,1,0,1,0)
AddVertex ( v,1,1,0,0,0)
AddVertex ( v,0,0,0,1,1)
AddVertex ( v,1,0,0,0,1)
AddTriangle( v,0,1,2)
AddTriangle( v,1,3,2)
Return sprite
End Function

Function CreateLeft()
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,0,1,1,0,0)
AddVertex ( v,0,1,0,1,0)
AddVertex ( v,0,0,1,1,1)
AddVertex ( v,0,0,0,0,1)
AddTriangle( v,0,1,2)
AddTriangle( v,1,3,2)
Return sprite
End Function

Function CreateRight()
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,1,1,0,1,0)
AddVertex ( v,1,1,1,0,0)
AddVertex ( v,1,0,0,0,1)
AddVertex ( v,1,0,1,1,1)
AddTriangle( v,0,1,2)
AddTriangle( v,1,3,2)
Return sprite
End Function

Function CreateTop()
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,0,1,1,0,0)
AddVertex ( v,1,1,1,0,0)
AddVertex ( v,0,1,0,1,0)
AddVertex ( v,1,1,0,1,0)
AddTriangle( v,0,1,2)
AddTriangle( v,1,3,2)
Return sprite
End Function

Function CreateBottom()
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,0,0,0,1,0)
AddVertex ( v,1,0,0,1,0)
AddVertex ( v,0,0,1,0,0)
AddVertex ( v,1,0,1,0,0)
AddTriangle( v,0,1,2)
AddTriangle( v,1,3,2)
Return sprite
End Function