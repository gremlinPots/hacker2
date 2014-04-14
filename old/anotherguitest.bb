;Ideas for cheats - all entered in the start login ID
;NOVAULTALARM - turns off all vault alarms
;BLINDGUARD - the guard can't see the MRU
;NOSECURITY - security camera's can't see the MRU
;MRUHASGOTDECOY - give the decoy document to the MRU


Graphics3D 640,480,32,0
SetBuffer BackBuffer()

cam1=CreateCamera():cam2=CreateCamera():cam3=CreateCamera():cam4=CreateCamera()

CameraRange cam1,1,10
CameraRange cam2,1,10
CameraRange cam3,1,10
CameraRange cam4,1,10

light=CreateLight()

; camera position values (unimportant)
cx#=0
cy#=0
cz#=-5

CameraViewport cam1,77,19,212,140
CameraViewport cam2,397,19,212,140
CameraViewport cam3,77,223,212,140
CameraViewport cam4,397,223,212,140

PositionEntity cam1,cx#,cy#,cz#
PositionEntity cam2,cx#,cy#,cz#
PositionEntity cam3,cx#,cy#,cz#
PositionEntity cam4,cx#,cy#,cz#

;Creating the 4 images that each camera will be rendered to
port1=CreateImage(212,140)
port2=CreateImage(212,140)
port3=CreateImage(212,140)
port4=CreateImage(212,140)

;Declaring arrays, so that the camera variables can be easily referenced

Dim cam(4):cam(1)=cam1:cam(2)=cam2:cam(3)=cam3:cam(4)=cam4
Dim port(4):port(1)=port1:port(2)=port2:port(3)=port3:port(4)=port4
Dim portxpos(4):portxpos(1)=77:portxpos(2)=397:portxpos(3)=77:portxpos(4)=397
Dim portypos(4):portypos(1)=19:portypos(2)=19:portypos(3)=222:portypos(4)=222
Dim staticport(4):staticport(1)=0:staticport(2)=0:staticport(3)=0:staticport(4)=0
Dim camnum(4):camnum(1)=1:camnum(2)=1:camnum(3)=1:camnum(4)=1
Dim monstate(4):monstate(1)=1:monstate(2)=1:monstate(3)=1:monstate(4)=1
Dim monnum(4):monnum(1)=1:monnum(2)=1:monnum(3)=1:monnum(4)=1
;camfunc tells the game what each monitor's function is: 0=Nothing(static), 1=Live Camera, 2=Security Monitor, 3=Video
Dim camfunc(4):camfunc(1)=0:camfunc(2)=0:camfunc(3)=0:camfunc(4)=0
Dim cambypass(4):cambypass(1)=0:cambypass(2)=0:cambypass(3)=0:cambypass(4)=0


SeedRnd MilliSecs() ;Seed the random number generator

;Variables
monselected=1

;Timer Variables
Dim mins(4):mins(0)=0:mins(1)=0:mins(2)=0:mins(3)=0:mins(4)=0
Dim secs(4):secs(0)=0:secs(1)=0:secs(2)=0:secs(3)=0:secs(4)=0
Dim milli(4):milli(0)=0:milli(1)=0:milli(2)=0:milli(3)=0:milli(4)=0
Dim completesecs(4):completesecs(0)=0:completesecs(1)=0:completesecs(2)=0:completesecs(3)=0:completesecs(4)=0
Dim fakemins(4):fakemins(0)=0:fakemins(1)=0:fakemins(2)=0:fakemins(3)=0:fakemins(4)=0
Dim secss$(4):secss$(0)="00":secss$(1)="00":secss$(2)="00":secss$(3)="00":secss$(4)="00"
Dim minss$(4):minss$(0)="00":minss$(1)="00":minss$(2)="00":minss$(3)="00":minss$(4)="00"
;Dim millicount(4):millicount(0)=MilliSecs():millicount(1)=MilliSecs():millicount(2)=MilliSecs():millicount(3)=MilliSecs():millicount(4)=MilliSecs()
Dim millicount(4):millicount(0)=0:millicount(1)=0:millicount(2)=0:millicount(3)=0:millicount(4)=0

Dim clockspeed(4):clockspeed(0)=2:clockspeed(1)=2:clockspeed(2)=2:clockspeed(3)=2:clockspeed(4)=2
Dim clocksecspeed(4):clocksecspeed(0)=1:clocksecspeed(1)=1:clocksecspeed(2)=1:clocksecspeed(3)=1:clocksecspeed(4)=1
Dim vtrstopped(4):vtrstopped(1)=0:vtrstopped(2)=0:vtrstopped(3)=0:vtrstopped(4)=0
Dim vtrrwff(4):vtrrwff(1)=0:vtrrwff(2)=0:vtrrwff(3)=0:vtrrwff(4)=0 ;vtrffrw signifies whether or not the fast forward/rewind button have been pushed in. 0=none 1=rewind 2=fastforward
rwspeed=-40:ffspeed=40

timecheck=MilliSecs()
secmonb=31

;Vhold variables (needs to be 1 for each viewport) - in arrays, again

Dim vholdpos#(4):vholdpos#(1)=0:vholdpos#(2)=0:vholdpos#(3)=0:vholdpos#(4)=0
Dim vholdspeed#(4):vholdspeed#(1)=Int(Rnd(-7,8))*5:vholdspeed#(2)=Int(Rnd(-7,8))*5:vholdspeed#(3)=Int(Rnd(-7,8))*5:vholdspeed#(4)=Int(Rnd(-7,8))*5

bandheight=12

;Scroller variables
Dim tx$(4):tx$(1)="":tx$(2)="":tx$(3)="":tx$(4)=""
Dim scrollgo(4):scrollgo(1)=0:scrollgo(2)=0:scrollgo(3)=0:scrollgo(4)=0
Dim go#(4):go#(1)=8:go#(2)=8:go#(3)=8:go#(4)=8
Dim scr_scroll(4):scr_scroll(1)=CreateImage(640,25):scr_scroll(2)=CreateImage(640,25):scr_scroll(3)=CreateImage(640,25):scr_scroll(4)=CreateImage(640,25)
Dim tx_pos(4):tx_pos(1)=1:tx_pos(2)=1:tx_pos(3)=1:tx_pos(4)=1
Dim textscroll$(9)
textscroll$(1)=" TGS - NORTH     "
textscroll$(2)=" TGS - EAST      "
textscroll$(3)=" TGS - SOUTH     "
textscroll$(4)=" TGS - WEST      "
textscroll$(5)="BYPASS CAMERA    "
textscroll$(6)="SECURITY MON A   "
textscroll$(7)="SECURITY MON B   "
textscroll$(8)="     LIVE        "
textscroll$(9)="     TAPE        "

Dim numxcoord(4):numxcoord(1)=19:numxcoord(2)=339:numxcoord(3)=19:numxcoord(4)=339
Dim numycoord(4):numycoord(1)=26:numycoord(2)=26:numycoord(3)=229:numycoord(4)=229

guilayer1=LoadImage("gui.png") ;Load the GUI graphic
guilayer2=LoadImage("alarmgui.png")
Dim guilayernum(2):guilayernum(1)=guilayer1:guilayernum(2)=guilayer2
MaskImage guilayer1,0,0,255
MaskImage guilayer2,0,0,255
knob1=LoadAnimImage("knob.png",10,10,0,16)
knob2=LoadAnimImage("alarmknob.png",10,10,0,16)
Dim knobnum(2):knobnum(1)=knob1:knobnum(2)=knob2
Dim knobrot(4)
MaskImage knob1,0,0,255
MaskImage knob2,0,0,255
guipush=LoadImage("guipush.png")
pushlayer=CreateImage(640,59)

numdisplay=CreateImage(640,480)
textcursor=CreateImage(640,480)

cursor=LoadImage("cursor.png") ;Load the cursor, set transparancy and hide the windows pointer
MaskImage cursor,0,17,119
HidePointer

;Text variables and bits
textbackground=CreateImage(640,480) ;Create the initial blue text background
SetBuffer ImageBuffer(textbackground)
Color 0,0,170
Rect 0,0,640,480
Color 255,255,255
SetBuffer BackBuffer()
textlayer=CreateImage(640,480)

textleftoffset=90;textleftoffset=30 ;pixel gap to leave between the left margin (multiple of 10)

;Images that will be used in the text
mrupic=LoadImage("mrupic.bmp")
mfsmpic=LoadImage("mfsmpic.bmp")
;Text variables and actual game text
fntcourier=LoadFont ("Courier",20,True,False,False) 
SetFont fntcourier
normaltypepause=300
quicktypepause=150
typepause=normaltypepause

;Image that is used for the static effect
wholestatic=LoadImage("static.png")

time=MilliSecs()
flasher=MilliSecs()
alarmtime=MilliSecs()

;Endof GUI SETUP

;Loading Textures for map
map=LoadTexture("map.png")
mrubmp=LoadTexture("mru.png")
annbmp=LoadTexture("ann.png")
guardbmp=LoadTexture("guard.png")
cambmp=LoadTexture("cam.png")

;TGS map
mapy#=0
tgsmap=CreateFloor(0,0,38,25)
EntityTexture tgsmap,map : EntityFX tgsmap,1
PositionEntity tgsmap,0.5,mapy#,-0.5

;Map Objects
mapmru=CreateFloor(0,0,1,1)
EntityTexture mapmru,mrubmp : EntityFX mapmru,1
mapann=CreateFloor(0,0,1,1)
EntityTexture mapann,annbmp : EntityFX mapann,1
mapguard=CreateFloor(0,0,1,1)
EntityTexture mapguard,guardbmp : EntityFX mapguard,1
mapcama=CreateFloor(0,0,0.99,1.5)
EntityTexture mapcama,cambmp : EntityFX mapcama,1
mapcamb=CreateFloor(0,0,0.99,1.5)
EntityTexture mapcamb,cambmp : EntityFX mapcamb,1

;Temporary Guard object
guard1=CreateCube() : EntityColor guard1,255,0,0
guard2=CreateCube() : EntityColor guard2,0,255,0
guard3=CreateCube() : EntityColor guard3,0,0,255
guard4=CreateCube() : EntityColor guard4,255,255,0

Dim guard(4):guard(1)=guard1:guard(2)=guard2:guard(3)=guard3:guard(4)=guard4
Dim guardx#(4):guardx#(0)=4:guardx#(1)=4::guardx#(2)=4:guardx#(3)=4:guardx#(4)=4
Dim guardz#(4):guardz#(0)=-4:guardz#(1)=-4:guardz#(2)=-4:guardz#(3)=-4:guardz#(4)=-4
Dim guardpos#(4)
Dim gdir$(4)
Dim guarddir(4)


For g=1 To 4
	ScaleEntity guard(g),0.5,1,0.5
	PositionEntity guard(g),guardx#(g),1,guardz#(g)
Next

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
mrumspeed#=0.035;0.01
mrutspeed#=6;1

;HideEntity mru

mruperfromaction=0

;Temporary Annihilator object
ann=CreateCube()
EntityColor ann,255,0,0
annx#=16
anny#=1
annz#=-4
ScaleEntity ann,0.5,1,0.5
PositionEntity ann,annx#,anny#,annz#
annmspeed#=0.052

EntityAlpha ann,0.5

;Hide the annilihator
;HideEntity ann ;may not even have to do this, since the ann start area shouldn't be seen
HideEntity mapann

;Initial TGS camera position
tgscamx=4
tgscamy=mapy#+6.5
tgscamz=-11
camtilt#=0

;Walkability Array, 38 by 25
Dim walkability(38,25)
bmp=LoadImage("bitmap.bmp")
For i=1 To 38
	For v=1 To 25
		walkability(i,v)=ReadPixel(i-1,v-1,ImageBuffer(bmp))
	Next
Next

;Freeing all images and textures previously loaded
FreeImage bmp
FreeTexture map
FreeTexture annbmp
FreeTexture guardbmp
FreeTexture cambmp

;Including external bb files
Include "camarray.bb"
Include "aStarLibrary.bb"
Include "textvar.bb"

;Vault alarms - remember to reset these if the game is restarted
Dim vaultalarm(4):vaultalarm(1)=0:vaultalarm(2)=0:vaultalarm(3)=0:vaultalarm(4)=0

;Initialising the game
mrudocument$=""
beenonce=0
mrunumber=3
guidisplay=0
textdisplay=1
showcursor=0
a=0 : b=185

guardon=1
onoff=1

;typepause=quicktypepause

;Frame Limiter declarations
Type FrameRate
	Field TargetFPS#	
	Field SpeedFactor#
	Field FPS#	
	Field TicksPerSecond	
	Field CurrentTicks	
	Field FrameDelay	
End Type

Global FL.FrameRate = New FrameRate

FrameLimitInit(30.0) ;Setting the desired fps

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)

;Do the SetSpeedFactor routine on every loop
Gosub SetSpeedFactor
;Set speeds in relation to the SpeedFactor
mrumovespeed#=mrumspeed# * FL\SpeedFactor
mruturnspeed#=mrutspeed# * FL\SpeedFactor
annmovespeed#=annmspeed# * FL\SpeedFactor
textspeed#=-7 * FL\SpeedFactor
textspace#=5.6 * FL\SpeedFactor
textstop#=8
vholdmove#=3 * FL\SpeedFactor ;The speed of the scroll when the screen is trying to right itself
vholdspeedchange#=5
vholdtopspeed#=(8*vholdspeedchange#) ;8 clicks
vholdbotspeed#=(-7*vholdspeedchange#) ;7 clicks


millisecscount=millisecscount + (4 * FL\SpeedFactor)



;The timer(s)
If stopclock=0 And mruspotted=0 And gameclockstart=1 ;added mruspotted=0 to make sure spotting events wouldn't cause an endless loop when the time still matched
For t=0 To 4
;Update the clock(s) * 5
;If MilliSecs() > millicount(t) + 40;(110 * FL\SpeedFactor)
If millisecscount > millicount(t) + (1 / FL\SpeedFactor)

If clockspeed(t) >= 0 ;If the clockspeed is greater or equal to 0 (going forwards) 

	If milli(t) >=60-clocksecspeed(t)
		milli(t)=0
		If secs(t) >=60-clocksecspeed(t)
			secs(t)=0
			completesecs(t)=completesecs(t)+1
			If fakemins(t) >=59
				secs(t)=0
				completesecs(t)=completesecs(t)+1
				fakemins(t)=0
				mins(t)=0
			Else
				fakemins(t)=fakemins(t)+1
				If mins(t) >= 4 : mins(t)=0 : Else : mins(t)=mins(t)+1 : EndIf
			EndIf
		Else
			secs(t)=secs(t)+clocksecspeed(t)
			completesecs(t)=completesecs(t)+clocksecspeed(t)
		EndIf
	Else
		milli(t)=milli(t)+clockspeed(t)
	EndIf
	
Else ;If the clockspeed is not greater than 0 (going backwards)
	
	If milli(t) <=clocksecspeed(t)-1 ;0 
		milli(t)=59
		If secs(t) <=clocksecspeed(t)-1 ;0
			secs(t)=59
			completesecs(t)=completesecs(t)-1
			If fakemins(t) <= 0
				secs(t)=59
				completesecs(t)=completesecs(t)-1
				fakemins(t)=59
				mins(t)=4
			Else
				fakemins(t)=fakemins(t)-1
				If mins(t) <= 0 : mins(t)=4 : Else : mins(t)=mins(t)-1 : EndIf
			EndIf
		Else
			secs(t)=secs(t)+(clocksecspeed(t)*-1)
			completesecs(t)=completesecs(t)+(clocksecspeed(t)*-1)
		EndIf
	Else
		milli(t)=milli(t)+clockspeed(t)
	EndIf

EndIf ;If clockspeed > 0
	
	If secs(t) < 10 : secss$(t)="0"+secs(t) : Else : secss$(t)=secs(t) : EndIf
	If fakemins(t) < 10 : minss$(t)="0"+fakemins(t) : Else : minss$(t)=fakemins(t) : EndIf
	;millicount(t)=MilliSecs()
	millicount(t)=millisecscount
EndIf ;If MilliSecs() > millicount(t) + 10
Next
EndIf ;If stopclock=0



If nocontrolmru=0 ;If MRU movement is allowed
;MRU movement
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
Else
mrumovex=0
mrumovez=0
EndIf ;If nocontrolmru=0

;Turning the MRU - not influenced by the nocontrolmru varible
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

If EntityX#(mru)-Int(EntityX#(mru))=0 And EntityZ#(mru)-Int(EntityZ#(mru))=0
	mrumovez=0 : mrumovex=0
EndIf

;Move the annihilator
If anngo=1
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
	If mrumovez=0 And mrumovex=0
	If Int(EntityX#(mru))=Int(EntityX(ann))+1 Or Int(EntityX#(mru))=Int(EntityX(ann)) Or Int(EntityX#(mru))=Int(EntityX(ann))-1
		If Int(EntityZ#(mru)) = Int(EntityZ#(ann)) Then Gosub StopMRU
		;Stop and take control away from mru
	ElseIf Int(EntityZ#(mru))=Int(EntityZ(ann))+1 Or Int(EntityZ#(mru))=Int(EntityZ(ann)) Or Int(EntityZ#(mru))=Int(EntityZ(ann))-1
		If Int(EntityX#(mru)) = Int(EntityX#(ann)) Then	Gosub StopMRU
	EndIf
	If nocontrolmru=1 And MilliSecs() > anntimecount + (8000 * FL\SpeedFactor) ;if the mru has been stopped, and it's been (time) since it stopped
		Gosub mrucaught
	EndIf
	EndIf ;If mrumovez=0 And mrumovex=0
EndIf ;If anngo=1
PositionEntity ann,annx#,1,annz#

;Destroy the MRU
If destmru=1
	;Destroy the mru
	HideEntity mru
	If MilliSecs() > alarmtime + (20000 * FL\SpeedFactor) ; Once 5 seconds have passed (or however long it takes to destroy the MRU)
		mrunumber=mrunumber-1
		mrudocument$=""
		;Display message 'Annihilation complete'
		guigo=0
		textdisplay=1
		showcursor=0
		a=0 : b=242
				
		destmru=0
		alarmtime=MilliSecs()
	EndIf ;If Millisecs() > alarmtime+5000
EndIf ;If destmru=1


;Find positions for the TGS camera
If Int(EntityX#(mru))>=4 And Int(EntityX#(mru))<=35 And Int(EntityZ#(mru))<=-4 And Int(EntityZ#(mru))>=-22
	tgscamx=Int(EntityX#(mru))
	tgscamy=mapy#+6.5
	tgscamz=Int(EntityZ#(mru))
EndIf

For t=0 To 4
;Update the guard's position
;Given a time, (mins*3600)+(secs#60) calculates either an x or a z coord for the guard
guardpos#(t)=(mins(t)*3600)+(secs(t)*60)+milli(t)
If guardpos#(t) <= 1080 : guardz#(t) = -4-(guardpos#(t)/120) : guardx#(t)=4 : gdir$(t)="Z" : guarddir(t)=-1
ElseIf guardpos#(t) <= 2280 : guardx#(t) = (guardpos#(t)/120)-5 : guardz#(t)=-13 : gdir$(t)="X" : guarddir(t)=1
ElseIf guardpos#(t) <= 2880 : guardz#(t) = 6-(guardpos#(t)/120) : guardx#(t)=14 : gdir$(t)="Z" : guarddir(t)=-1
ElseIf guardpos#(t) <= 4080 : guardx#(t) = 38-(guardpos#(t)/120) : guardz#(t)=-18 : gdir$(t)="X" : guarddir(t)=-1
ElseIf guardpos#(t) <= 4440 : guardz#(t) = 16-(guardpos#(t)/120) : guardx#(t)=4 : gdir$(t)="Z" : guarddir(t)=-1
ElseIf guardpos#(t) <= 5640 : guardx#(t) = (guardpos#(t)/120)-33 : guardz#(t)=-21 : gdir$(t)="X" : guarddir(t)=1
ElseIf guardpos#(t) <= 7680 : guardz#(t) = (guardpos#(t)/120)-68 : guardx#(t)=14 : gdir$(t)="Z" : guarddir(t)=1
ElseIf guardpos#(t) <= 8160 : guardx#(t) = -50+(guardpos#(t)/120) : guardz#(t)=-4 : gdir$(t)="X" : guarddir(t)=1
ElseIf guardpos#(t) <= 10200 : guardz#(t) = 64-(guardpos#(t)/120) : guardx#(t)=18 : gdir$(t)="Z" : guarddir(t)=-1
ElseIf guardpos#(t) <= 11400 : guardx#(t) = (guardpos#(t)/120)-67 : guardz#(t)=-21 : gdir$(t)="X" : guarddir(t)=1
ElseIf guardpos#(t) <= 11760 : guardz#(t) = (guardpos#(t)/120)-116 : guardx#(t)=28 : gdir$(t)="Z" : guarddir(t)=1
ElseIf guardpos#(t) <= 12600 : guardx#(t) = (guardpos#(t)/120)-70 : guardz#(t)=-18 : gdir$(t)="X" : guarddir(t)=1
ElseIf guardpos#(t) <= 13200 : guardz#(t) = (guardpos#(t)/120)-123 : guardx#(t)=35 : gdir$(t)="Z" : guarddir(t)=1
ElseIf guardpos#(t) <= 14040 : guardx#(t) = 145-(guardpos#(t)/120) : guardz#(t)=-13 : gdir$(t)="X" : guarddir(t)=-1
ElseIf guardpos#(t) <= 15120 : guardz#(t) = (guardpos#(t)/120)-130 : guardx#(t)=28 : gdir$(t)="Z" : guarddir(t)=1
ElseIf guardpos#(t) <= 18000 : guardx#(t) = 154-(guardpos#(t)/120) : guardz#(t)=-4 : gdir$(t)="X" : guarddir(t)=-1
EndIf
PositionEntity mapguard,Int(guardx#(0))-0.49,mapy#+0.0005,Int(guardz#(0))+0.485
PositionEntity guard(1),guardx#(2),1,guardz#(2)
PositionEntity guard(2),guardx#(3),1,guardz#(3)
PositionEntity guard(3),guardx#(4),1,guardz#(4)
PositionEntity guard(4),guardx#(1),1,guardz#(1)
Next

;Positioning map objects
PositionEntity mapmru,Int(EntityX#(mru))-0.49,mapy#+0.001,Int(EntityZ#(mru))+0.485
PositionEntity mapann,Int(EntityX#(ann))-0.49,mapy#+0.0011,Int(EntityZ#(ann))+0.485
PositionEntity mapcama,camarray(secmona,0)-0.478,mapy#+0.0012,camarray(secmona,1)+0.8
PositionEntity mapcamb,camarray(secmonb,0)-0.478,mapy#+0.0012,camarray(secmonb,1)+0.8


;Calculate which cameras are being monitored
If secs(0)<2 : secmona=30 : Else : secmona=Int(secs(0)/2) : EndIf		
If twoseconds+1<completesecs(0) ;Every two seconds
	If secmonb>37 : secmonb=31 : Else : secmonb=secmonb+1 : EndIf
	twoseconds=completesecs(0)
	dontcheckbyp=0
	For v=1 To 4
		If camfunc(v)=2 Then vholdpos#(v)=110
	Next
EndIf


If mruspotted=1
;Only set off alarm once MRU has reached it's target.
	If mrumovez=0 And mrumovex=0
		;Play Siren
		;Stop the clocks
		stopclock=1
		;Pause for x seconds while you play the siren
		If MilliSecs()>=alarmtime+(12000 * FL\SpeedFactor) ;if three seconds have passed
		;Stop Siren
		;Hide all the guards - now done elsewhere
			;HideEntity guard
			;HideEntity mapguard
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
		ElseIf whosawmru=3
			;'Timecode error identified'			
			a=0 : b=186
		EndIf
		;Tell the code to use the text loop instead of the graphics loop
		guigo=0
		textdisplay=1
		showcursor=0
		
		mruspotted=0
		alarmtime=MilliSecs()
		
		EndIf; If Millisecs()>=alarmtime+3000
	EndIf
EndIf

;Switching the cameras back to default time
For xj=1 To 4
	If camfunc(xj)=2; And mins(xj)<>mins(0) And secs(xj)<>secs(0)
		resetmon=1
		For xi=1 To 4 ;Go through all the monitors to see if any match the current monnum
			If cambypass(xi)=1 And camnum(xi)=monnum(xj)
				resetmon=0
			EndIf
		Next
		If resetmon=1
			mins(xj)=mins(0)
			secs(xj)=secs(0)
			milli(xj)=milli(0)
			completesecs(xj)=completesecs(0)
			fakemins(xj)=fakemins(0)
			secss$(xj)=secss$(0)
			minss$(xj)=minss$(0)
			millicount(xj)=millicount(0)
		EndIf
	EndIf
Next

;Finding the MRU. Indepentant of the gui / text screens, because the MRU can be found whenever either is in operation
For i=1 To 4 ;Checking for bypassed cameras and seeing if the timecode matches
	If cambypass(i)=1
		For xi=1 To 4	
			If camfunc(xi)=2 And camnum(i)=monnum(xi)
				mins(xi)=mins(i)
				secs(xi)=secs(i)
				milli(xi)=milli(i)
				completesecs(xi)=completesecs(i)
				fakemins(xi)=fakemins(i)
				secss$(xi)=secss$(i)
				minss$(xi)=minss$(i)
				millicount(xi)=millicount(i)
			EndIf ;if camfunc(xi)=2 and camnum(i)=monnum(xi)
		Next
		If camnum(i)=secmona Or camnum(i)=secmonb ;if the bypassed camera num equals a currently monitored camera
		
		;Check to see if an alarm needs triggering
		If stopclock=0 ;only check if the clock is not stopped (ie, if it's playing)
			If dontcheckbyp=0 ;and only check if we want the bypass to be found (i.e. not if it's right after one has already been found)
			If secs(i)<>secs(0) Or mins(i)<>mins(0) ;If a timecode error IS found
				mruspotted=1 : whosawmru=3 : alarmtime=MilliSecs()
				dontcheckbyp=1
			EndIf
			EndIf
		EndIf ;If stopclock=0
		
		EndIf ;If camnum(i)=secmona or camnum(i)=secmonb
	EndIf
Next



;If the GUI is activated, do the following -------------
If guigo=1 And textdisplay=0

;Checking the position and direction of the mru to see if the computer interface needs to be started
If checkdirection=0
If Int(EntityX#(mru))=33 And Int(EntityZ#(mru))=-7 And mrudirection=1 And mruperformaction=0 ;Terminal A
	a=0 : b=282 : guigo=0 : textdisplay=1 : showcursor=0 : mruterminal=1
ElseIf Int(EntityX#(mru))=26 And Int(EntityZ#(mru))=-16 And mrudirection=1 And mruperformaction=0 ;Terminal B
	a=0 : b=282 : guigo=0 : textdisplay=1 : showcursor=0 : mruterminal=2
ElseIf Int(EntityX#(mru))=8 And Int(EntityZ#(mru))=-7 And mrudirection=1 And mruperformaction=0 ;Terminal C
	a=0 : b=282 : guigo=0 : textdisplay=1 : showcursor=0 : mruterminal=3
ElseIf Int(EntityX#(mru))=12 And Int(EntityZ#(mru))=-16 And mrudirection=1 And mruperformaction=0 ;Terminal D
	a=0 : b=282 : guigo=0 : textdisplay=1 : showcursor=0 : mruterminal=4
ElseIf Int(EntityX#(mru))=26 And Int(EntityZ#(mru))=-4 And mrudirection=1 And mruperformaction=0 ;Safe Terminal
	a=0 : b=282 : guigo=0 : textdisplay=1 : showcursor=0 : mruterminal=5
EndIf
EndIf ;If checkdirection=0
;Checking to see if the FBI messages need to be displayed
If checkfbipos=0
If Int(EntityX#(mru))=25 And Int(EntityZ#(mru))=-24 And mruperformaction=0 ;FBI Warning screen
	If mrudocument$<>"" And mrudocument$<>"DECOY"
		beenonce=1
		a=0 : b=392 : guigo=0 : textdisplay=1 : showcursor=0
	ElseIf mrudocument$="DECOY"
		If beenonce=1
			a=0 : b=460 : guigo=0 : textdisplay=1 : showcursor=0
		Else
			a=0 : b=435 : guigo=0 : textdisplay=1 : showcursor=0
		EndIf
	EndIf
	checkdirection=1
EndIf
EndIf ;If checkfbipos=0


For i = 1 To 4

;Positioning the cameras
	If camfunc(i)=1 Or camfunc(i)=3
		PositionEntity cam(i),camarray(camnum(i),0),1,camarray(camnum(i),1)
	ElseIf camfunc(i)=2
		If monstate(i)=1 Then monnum(i)=secmona
		If monstate(i)=2 Then monnum(i)=secmonb
		PositionEntity cam(i),camarray(monnum(i),0),1,camarray(monnum(i),1)
	ElseIf camfunc(i)=4
		PositionEntity cam(i),tgscamx,tgscamy,tgscamz
	EndIf
	
	RenderWorld ;RenderWorld now, as the cameras are in the right places


;Various vholdspeed# and vholdpos# operations


	If vholdspeed#(i) > vholdtopspeed#
		vholdspeed#(i) = vholdbotspeed#
	ElseIf vholdspeed#(i) < vholdbotspeed#
		vholdspeed#(i) = vholdtopspeed#
	EndIf
	knobrot(i)=((vholdspeed#(i)+40)/5)-1
	If vholdspeed#(i) <> 0
		vholdpos#(i)=vholdpos#(i)+(vholdspeed#(i)*FL\SpeedFactor)
	Else 
		If vholdpos#(i) <> 0
			If vholdpos#(i) >= 70 Then vholdpos#(i)=vholdpos#(i)+vholdmove# Else vholdpos#(i)=vholdpos#(i)-vholdmove#
		EndIf
	EndIf
	If vholdpos#(i) >= 140+bandheight
		vholdpos#(i)=0
	ElseIf vholdpos#(i) <= 0
		vholdpos#(i)=140+bandheight
	EndIf
	
	HideEntity guard(1) : HideEntity guard(2) : HideEntity guard(3) : HideEntity guard(4)
	
	If i=1
		GrabImage port(i),77,19
	ElseIf i=2
		GrabImage port(i),397,19
	ElseIf i=3
		GrabImage port(i),77,223
	Else
		GrabImage port(i),397,223
	EndIf
	
	If i+1>4 : j=1 : Else : j=i+1 : EndIf ;making up for the strange 'top right monitor displays mon 1 view'
	If camfunc(j)=3 ;If the camera is switched to video mode, hide the mru and the annihilator
		HideEntity mru
		HideEntity ann
	ElseIf camfunc(j)=2 ;If the camera is switched to monitor mode, but has been bypassed, also hide the mru and ann
		For o=1 To 4 ;Go through all the monitors to see if any match the current monnum
			If cambypass(o)=1 And camnum(o)=monnum(j)
				HideEntity mru
				HideEntity ann
			EndIf
		Next
	Else
		If mrucaught=0 Then ShowEntity mru
		ShowEntity ann
	EndIf
	
	If anngo<>1 And guardon=1 Then ShowEntity guard(i) : ShowEntity mapguard
	
		
	If camfunc(i)<>4
	SetBuffer ImageBuffer(port(i)) ;Video effects - Scanlines and timestamp
	Color 0,0,0 : For p=1 To 140 Step 2 : Rect 0,p,212,1 : Next ;Scanlines
	;FF/RW/PAUSE lines used to go here, but moved so that they can be drawn at the same x and y pos regardless of v-hold
	If vtrstopped(i)=1 ;if the video screen is stopped, blank the port image out
		Color 100,100,100 : Rect 0,0,212,140
	EndIf
	Color 0,0,0 : Rect 49,120,114,20 ;black text background
	Color 255,255,255 : Text 73,121,minss$(i)+":"+secss$(i) ;white timestamp
	
	SetBuffer BackBuffer()
	EndIf ;If camfunc(i)<>4
	
	Cls
	
	SetBuffer ImageBuffer(numdisplay)
	Cls
	SetBuffer BackBuffer()

	For a=1 To 4
	
		If camfunc(a)=1 Or camfunc(a)=2 Or camfunc(a)=3 Or camfunc(a)=4
			DrawBlockRect port(a),portxpos(a),portypos(a),0,vholdpos#(a),212,140-vholdpos#(a)
			DrawBlockRect port(a),portxpos(a),portypos(a)+(140-vholdpos#(a))+bandheight,0,0,212,140-(140-vholdpos#(a))-bandheight
		ElseIf camfunc(a)=0
			DrawBlockRect wholestatic,portxpos(a),portypos(a),Rand(88),Rand(160),212,140
		EndIf
		If camfunc(a)=1 And camnum(a)=28 Then DrawBlockRect wholestatic,portxpos(a),portypos(a),Rand(88),Rand(160),212,140
		If camfunc(a)=2 And monnum(a)=28 Then DrawBlockRect wholestatic,portxpos(a),portypos(a),Rand(88),Rand(160),212,140
		If camfunc(a)=3 And camnum(a)=28 Then DrawBlockRect wholestatic,portxpos(a),portypos(a),Rand(88),Rand(160),212,140

		If camfunc(i)<>4 ;Draw the FF/RW lines on screen
		If clockspeed(a)=ffspeed Or clockspeed(a)=rwspeed 	
			DrawBlockRect wholestatic,portxpos(a),portypos(a)+35,Rand(88),Rand(160),212,7
			DrawBlockRect wholestatic,portxpos(a),portypos(a)+97,Rand(88),Rand(160),212,7
		ElseIf clockspeed(a)=4 Or clockspeed(a)=-4
			DrawBlockRect wholestatic,portxpos(a),portypos(a)+35,Rand(88),Rand(160),212,7
		EndIf
		If clockspeed(a)=0 And vtrstopped(a)<>1 ;Draw PAUSE line
			DrawBlockRect wholestatic,portxpos(a),portypos(a)+35,Rand(88),Rand(160),212,7
		EndIf
		EndIf ;If camfunc(i)<>4
	
	;Number display operations (also sharing the loop)
	Color 66,200,99
	If camnum(a)<10 Then camnums$="0"+camnum(a) Else camnums$=camnum(a)
	If monnum(a)<10 Then monnums$="0"+monnum(a) Else monnums$=monnum(a)
	If camfunc(a)=1 Or camfunc(a)=3
		;Display the camnum
		SetBuffer ImageBuffer(numdisplay)
		Text numxcoord(a),numycoord(a),camnums$
		SetBuffer BackBuffer()
	ElseIf camfunc(a)=2
		;Display the monnum
		SetBuffer ImageBuffer(numdisplay)
		Text numxcoord(a),numycoord(a),monnums$
		SetBuffer BackBuffer()
	ElseIf camfunc(a)=0 Or camfunc(a)=4
		;Display nothing
		SetBuffer ImageBuffer(numdisplay)
		Text numxcoord(a),numycoord(a),"--"
		SetBuffer BackBuffer()
	EndIf
	Color 255,255,255
	Next
	
	;Text scroller operations (sharing the 1 to 4 loop of another operation)
	If scrollgo(i)=1
		go#(i)=go#(i)+textspace#
		If go#(i)>textstop#
			SetBuffer ImageBuffer(scr_scroll(i))
			go#(i)=0
			Color 255,0,0 : Text 229,0,Mid$(tx$(i),tx_pos(i),1) : Color 255,255,255
			tx_pos(i)=tx_pos(i)+1 : If tx_pos(i) > Len(tx$(i)) Then tx_pos(i)=1 : scrollgo(i)=0 : tx$(i)=""
			SetBuffer BackBuffer()
		EndIf
		SetBuffer ImageBuffer(scr_scroll(i))
		DrawBlock scr_scroll(i),textspeed#,0
		SetBuffer BackBuffer()
	EndIf

	;Flash the selected monitor number on and off
	If MilliSecs() > flasher+(2000 * FL\SpeedFactor) : If flashon=0 : flashon=1 : ElseIf flashon=1 : flashon=0 : EndIf : If onoff=2 : onoff=1 Else onoff=onoff+1 : EndIf : flasher=MilliSecs() : EndIf
	If flashon=1
	SetBuffer ImageBuffer(numdisplay)
	Color 0,0,0 : Rect numxcoord(monselected),numycoord(monselected),30,20 : Color 255,255,255
	SetBuffer BackBuffer()
	EndIf
	
	Gosub cursorcheck
	
	;All the drawimage stuff is moved down, out of the 'Next' loop
	
	;RenderWorld ;RenderWorld is now called 'higher up' the code, when the cameras are put in their proper positions
Next

	;Drawing the guilayer (working out if the red 'alarm' version needs to be drawn instead)
	If stopclock=1
		DrawImage guilayernum(onoff),0,0
	Else
		DrawImage guilayernum(1),0,0
	EndIf
	
	For k=1 To 4
		If stopclock=1
			DrawImage knobnum(onoff),portxpos(k)-49,portypos(k)+51,knobrot(k)
		Else
			DrawImage knobnum(1),portxpos(k)-49,portypos(k)+51,knobrot(k)
		EndIf
	Next
	
	DrawImage pushlayer,0,421
	
	DrawImage scr_scroll(1),64,177
	DrawImage scr_scroll(2),384,177
	DrawImage scr_scroll(3),64,380
	DrawImage scr_scroll(4),384,380
	
	DrawImage numdisplay,0,0

	DrawImage cursor,MouseX()-7,MouseY()-2

	Text 0,0,vholdspeed#(monselected)

	Text 0,20,FL\SpeedFactor

	
	Flip


EndIf ;guigo=1

;-------------

If guigo=0 And textdisplay=1
	
	Gosub printtext

	DrawImage textbackground,0,0
	DrawImage textlayer,textleftoffset+1,0
	If textcursorhide=0 Then DrawImage textcursor,0,0
	
	SetBuffer ImageBuffer(textcursor) : Cls : SetBuffer BackBuffer()
	
	Gosub cursorcheck

	Flip
	
EndIf ;guigo=0

Wend

End

;END OF MAIN GAME CODE
;-------------

;SUBROUTINES:

;DEALING WITH THE Text
.printtext

If MilliSecs()>time+(typepause * FL\SpeedFactor)
	If needtoreseta=1 : a=0 : needtoreseta=0 : EndIf
	If msg(b,1)="CLS"
		FlushKeys
		SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer() : a=0 : b=b+1 : Delay 500
	ElseIf msg(b,1)="DIAL"
		pausetime#=MilliSecs()+5000
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : Gosub cursorcheck : Flip
		Wend
		a=0 : b=b+1 ;Play the dialing sounds
	ElseIf msg(b,1)="GOBACK"
		pausetime#=1000+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : Gosub cursorcheck : Flip
		Wend
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer() : a=0 : b=b-3
	ElseIf Left$(msg(b,1),3)="PAU"
		pausetime# = Right$(msg(b,1),1) : pausetime#=(pausetime#*1000)+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : Gosub cursorcheck : Flip
		Wend
		;Delay (pausetime#*1000)
		b=b+1
	ElseIf msg(b,1)="LOGON"
		Print ""
		Print ""
		Print ""
		Print ""
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		logonname$=Input(String$(" ",(textleftoffset/10)-2)+"LOGON PLEASE: ")
		textcursorhide=0
		logonname$=Upper$(logonname$)
		typepause=quicktypepause
		msg(8,0)=msg(8,0)+logonname$
		a=14
		b=b+1
	ElseIf msg(b,1)="COMMANDMRU"
		DrawImage textlayer,textleftoffset+1,0 : Flip
		command$=Input(String$(" ",(textleftoffset/10)-2)+"MRU COMMAND: ")
		command$=Upper$(command$)
		msg(285,0)=msg(285,0)+command$
		checkdirection=1
		a=13
		b=b+1
	ElseIf msg(b,1)="FBIMESSAGE"
		checkfbipos=1
		a=0
		b=b+1
	ElseIf msg(b,1)="ACCESSCODE"
		DrawImage textlayer,textleftoffset+1,0 : Flip
		For l=1 To 4
			Print ""
		Next
		code$=Input(String$(" ",(textleftoffset/10)-2)+"ENTER ACCESS CODE: ")
		code$=Upper$(code$)
		msg(303,0)=msg(303,0)+code$
		a=19
		b=b+1
	ElseIf msg(b,1)="ENTERCOMB"
		DrawImage textlayer,textleftoffset+1,0 : Flip
		For l=1 To 4
			Print ""
		Next
		comb$=Input(String$(" ",(textleftoffset/10)-2)+"ENTER COMBINATION: ")
		comb$=Upper$(comb$)
		msg(340,0)=msg(340,0)+comb$
		a=19
		b=b+1
	ElseIf msg(b,1)="CHECKCOMMAND"
		pausetime#=1000+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : Gosub cursorcheck : Flip
		Wend
		If command$="ROA"
			;goto the roa routine
			a=0 : b=293
		ElseIf command$=""
			;exit
			a=0 : b=290
			msg(290,1)=20
		Else
			;goto error message
			a=0 : b=b+1
		EndIf
		msg(285,0)="             "
	ElseIf msg(b,1)="CHECKCOMB"
		pausetime#=1000+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : Gosub cursorcheck : Flip
		Wend
		;If the alarm has been turned off
		If vaultalarm(1)=1 And vaultalarm(2)=1 And vaultalarm(3)=1 And vaultalarm(4)=1
			If comb$="07041776" ;And if the combination is right, goto the right line
				a=0 : b=345
			ElseIf comb$="" ;If the combination is blank, exit
				a=0 : b=290
				msg(290,1)=120
			Else ;If the combination is wrong, display error message and re-enter
				a=0 : b=b+1
			EndIf
		Else ;If the alarm has NOT been turned off
			If comb$="07041776" ;And the combination is right, turn on the alarm
				mruspotted=1 : whosawmru=0 : alarmtime=MilliSecs() : a=0 : b=221
			ElseIf comb$="" ;Exit
				a=0 : b=290
				msg(290,1)=120
			Else ;Display error message and re-enter
				a=0 : b=b+1
			EndIf
		EndIf
		msg(340,0)="                   "
	ElseIf msg(b,1)="CHECKCODE"
		pausetime#=1000+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0 : Gosub cursorcheck : Flip
		Wend
		If mruterminal=1 And code$="RED 7" ;Depending on the terminal, set the text to whatever that terminal contains
			a=0 : b=308
			vaultalarmnum=2 : vaultalarmtext$="2"
			msg(313,0)="'04'"
			msg(315,0)="ACCESS CODE TO SECOND FILING CABINET-"
			msg(316,0)="'WHITE 6'"
		ElseIf mruterminal=2 And code$="WHITE 6"
			a=0 : b=308
			vaultalarmnum=4 : vaultalarmtext$="4"
			msg(313,0)="'76'"
			msg(315,0)="ACCESS CODE TO THIRD FILING CABINET-"
			msg(316,0)="'BLUE 1'"
		ElseIf mruterminal=3 And code$="BLUE 1"
			a=0 : b=308
			vaultalarmnum=1 : vaultalarmtext$="1"
			msg(313,0)="'07'"
			msg(315,0)="ACCESS CODE TO FOURTH FILING CABINET-"
			msg(316,0)="'WHITE 50'"
		ElseIf mruterminal=4 And code$="WHITE 50"
			a=0 : b=308
			vaultalarmnum=3 : vaultalarmtext$="3"
			msg(313,0)="'17'"
			msg(315,0)=""
			msg(316,0)=""
		ElseIf code$=""
			;exit
			a=0 : b=290
			msg(290,1)=120
		Else
			;goto error message
			a=0 : b=b+1
		EndIf
		msg(303,0)="                   "
	ElseIf msg(b,1)="REENTERCOMMAND"
		SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer()
		a=0 : b=283
	ElseIf msg(b,1)="REENTERCODE"
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,79,640,40,1 : Color 255,255,255 : SetBuffer BackBuffer()
		a=0 : b=301
	ElseIf msg(b,1)="REENTERCOMB"
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,79,640,40,1 : Color 255,255,255 : SetBuffer BackBuffer()
		a=0 : b=338
	ElseIf msg(b,1)="CHECKTERM"
		If mruterminal=1 Or mruterminal=2 Or mruterminal=3 Or mruterminal=4 ;One of the filing cabinets
			a=0 : b=297
		ElseIf mruterminal=5 ;The safe
			a=0 : b=334
		EndIf
	ElseIf msg(b,1)="VAULTCHECK"
		If vaultalarm(vaultalarmnum)=0 ;If it hasn't been turned on yet
			a=0 : b=319
		ElseIf vaultalarm(vaultalarmnum)=1 ;If it has already been turned on
			a=0 : b=329
			msg(329,0)="VAULT ALARM #"+vaultalarmtext$+" DISABLED."
		EndIf
	ElseIf msg(b,1)="VAULTYN"
		DrawImage textlayer,textleftoffset+1,0 : Flip
		For l=1 To 9
			Print ""
		Next
		vaultyn$=Input(String$(" ",(textleftoffset/10)-2)+"SET SWITCH TO 'OFF' POSITION? (Y/N) ")
		vaultyn$=Upper$(vaultyn$)
		msg(322,0)=msg(322,0)+vaultyn$
		a=36
		b=b+1
	ElseIf msg(b,1)="DOCUYN"
		DrawImage textlayer,textleftoffset+1,0 : Flip
		linenum=msg(b-1,1)
		For l=1 To linenum/20
			Print ""
		Next
		docuyn$=Input(String$(" ",(textleftoffset/10)-2)+"REMOVE DOCUMENT FROM FILE? (Y/N) ")
		docuyn$=Upper$(docuyn$)
		msg(b+1,0)=msg(b+1,0)+docuyn$
		a=33
		b=b+1
	ElseIf msg(b,1)="VAULTYNCHECK"
		If vaultyn$="Y"
			;Check all the previous alarms to see if they've been turned off in order
			If vaultalarmnum=1 Then vaultalarm(1)=1 : a=0 : b=324
			If vaultalarmnum=2 Then If vaultalarm(1)=1 Then vaultalarm(2)=1 : a=0 : b=324 Else mruspotted=1 : whosawmru=0 : alarmtime=MilliSecs() : a=0 : b=231 ;Set off alarm and set all switches to off
			If vaultalarmnum=3 Then If vaultalarm(1)=1 And vaultalarm(2)=1 Then vaultalarm(3)=1 : a=0 : b=324 Else mruspotted=1 : whosawmru=0 : alarmtime=MilliSecs() : a=0 : b=231 ;Set off alarm
			If vaultalarmnum=4 Then If vaultalarm(1)=1 And vaultalarm(2)=1 And vaultalarm(3)=1 Then vaultalarm(4)=1 : a=0 : b=324 Else mruspotted=1 : whosawmru=0 : alarmtime=MilliSecs() : a=0 : b=231 ;Set off alarm
			msg(233,0)="VAULT ALARM #"+vaultalarmtext$+" DISABLED OUT" ;updating the arrays with correct vaultnumbers, even if not needed
			msg(325,0)="VAULT ALARM #"+vaultalarmtext$+" DISABLED."
		ElseIf vaultyn$="N"
			;goto to '(return)' part of the text routine
			a=0 : b=330
		Else
			SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,179,640,40,1 : Color 255,255,255 : SetBuffer BackBuffer()
			a=0 : b=320
		EndIf
		msg(322,0)="                                    "
	ElseIf msg(b,1)="DOCUYNCHECK"
		If docuyn$="Y"
			If b=356 ;Then it's the classified document
				mrudocument$="CLASS"
				msg(383,1)=140 : msg(385,1)=160 : msg(387,1)=200 : msg(389,1)=220 ;Set the text to the correct line
			ElseIf b=363 ;Then it's the purchase orders
				mrudocument$="PURCH"
				msg(383,1)=200 : msg(385,1)=220 : msg(387,1)=260 : msg(389,1)=280 ;Set the text to the correct line
			ElseIf b=370 ;Then it's the requisitions
				mrudocument$="REQUI"
				msg(383,1)=260 : msg(385,1)=280 : msg(387,1)=320 : msg(389,1)=340 ;Set the text to the correct line
			ElseIf b=377 ;Then it's the decoy document
				mrudocument$="DECOY"
				msg(383,1)=340 : msg(385,1)=360 : msg(387,1)=400 : msg(389,1)=420 ;Set the text to the correct line
			EndIf
			msg(b-1,0)="                                 "
			a=0 : b=382
		ElseIf docuyn$="N"
			msg(b-1,0)="                                 "
			a=0 : b=b+1
		Else
			lineblock=msg(b-3,1)
			SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,lineblock-1,640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
			msg(b-1,0)="                                 "
			a=0 : b=b-3
		EndIf
	ElseIf msg(b,1)="Q2"
		For l=1 To 12
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"INPUT FROM SURVEILLANCE CAMERAS: ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="2"
		Gosub checkanswer
	ElseIf msg(b,1)="Q3"
		For l=1 To 13
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"INPUT FROM PRE-RECORDED TAPES:   ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="3"
		Gosub checkanswer
	ElseIf msg(b,1)="Q6"
		For l=1 To 14
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"CAMERA BYPASS SWITCH:            ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="6"
		Gosub checkanswer
	ElseIf msg(b,1)="Q4"
		For l=1 To 15
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"OUTPUT TO SECURITY MONITORS:     ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="4"
		Gosub checkanswer
	ElseIf msg(b,1)="Q1"
		For l=1 To 16
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"INPUT FROM MFSM PANEL CONTROLS:  ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="1"
		Gosub checkanswer
	ElseIf msg(b,1)="Q7"
		For l=1 To 17
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"PHNORDMAN VIDEO MATRIX (PVM):    ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="7"
		Gosub checkanswer
	ElseIf msg(b,1)="Q5"
		For l=1 To 18
			Print ""
		Next
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		DrawImage textlayer,textleftoffset+1,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-2)+"OUTPUT TO MFSM DISPLAY:          ")
		textcursorhide=0
		answer$=Upper$(answer$)
		trueanswer$="5"
		Gosub checkanswer
	ElseIf Left$(msg(b,1),1)="M"
		If buttonpress=Right$(msg(b,1),1)
			a=0
			b=b+3
		ElseIf buttonpress<>0
			SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
			a=0
			b=b+1
		EndIf	
	ElseIf msg(b,1)="HM6"
		If buttonpress=6
			a=0
			b=b+3
			showcursor=0
		ElseIf buttonpress<>0
			SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
			a=0
			b=b+1
		EndIf
	ElseIf msg(b,1)="RESETP"
		typepause=normaltypepause
		a=0 : b=b+1
		If logonname$="00987" Then a=0 : b=185 : typepause=quicktypepause
		logonname$=""
	ElseIf msg(b,1)="CHECKANSWER"
		If answerwrong=0
			a=0 : b=b+1
		Else
			SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
			Delay 500 : a=0 : b=b-3
		EndIf
	ElseIf msg(b,1)="SETQUICKP"
		typepause=quicktypepause
		a=0 : b=b+1
	ElseIf msg(b,1)="COUNTMRU"
		If mrunumber=2 : a=0 : b=253 : EndIf
		If mrunumber=1 : a=0 : b=261 : EndIf
		If mrunumber=0 : a=0 : b=269 : EndIf
	ElseIf msg(b,1)="PICMRU"
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textlayer)
			DrawBlockRect mrupic,textleftoffset,140,0,0,500,picnum ;Image is 500 X 90 pixels
			SetBuffer BackBuffer()
			DrawImage textlayer,textleftoffset+1,0 : Flip
		Until picnum=90
		a=0 : b=b+1
		textcursorhide=0
	ElseIf msg(b,1)="PICMFSM"
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		SetBuffer ImageBuffer(textbackground)
		Color 136,136,136
		Rect 0,0,640,480
		Color 255,255,255
		SetBuffer BackBuffer()
		DrawImage textbackground,0,0
		DrawImage textlayer,textleftoffset+1,0
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textlayer)
			DrawBlockRect mfsmpic,textleftoffset-10,10,0,0,532,picnum ;Image is 532 X 214 pixels
			SetBuffer BackBuffer()
			DrawImage textlayer,textleftoffset+1,0 : Flip
		Until picnum=214
		a=0 : b=b+1
		textcursorhide=0
	ElseIf msg(b,1)="TEXTBGGREY"
		SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer()
		SetBuffer ImageBuffer(textbackground)
		Color 136,136,136
		Rect 0,0,640,480
		Color 255,255,255
		SetBuffer BackBuffer()
		a=0 : b=b+1
	ElseIf msg(b,1)="TEXTBGBLUE"
		SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer()
		SetBuffer ImageBuffer(textbackground)
		Color 0,0,170
		Rect 0,0,640,480
		Color 255,255,255
		SetBuffer BackBuffer()
		a=0 : b=b+1
	ElseIf msg(b,1)="GUIPIC"
		textcursorhide=1 : 	DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset+1,0
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textbackground)
			DrawBlockRect guilayer1,0,421,0,421,640,picnum
			SetBuffer BackBuffer()
			DrawImage textbackground,0,0 : Flip
		Until picnum=59
		showcursor=1
		a=0 : b=b+1
		textcursorhide=0
	ElseIf msg(b,1)="WAITRETURN"	
		Repeat
		Until KeyDown(28)=True
		a=0 : b=b+1
		FlushKeys
	ElseIf msg(b,1)="GUISWITCH"
		guigo=1 : textdisplay=0 : showcursor=1 : gameclockstart=1
	ElseIf msg(b,1)="GUIFROMMSFMTEST"
		camnum(1)=1:camnum(2)=1:camnum(3)=1:camnum(4)=1
		monstate(1)=1:monstate(2)=1:monstate(3)=1:monstate(4)=1
		monnum(1)=1:monnum(2)=1:monnum(3)=1:monnum(4)=1
		camfunc(1)=0:camfunc(2)=0:camfunc(3)=0:camfunc(4)=0
		cambypass(1)=0:cambypass(2)=0:cambypass(3)=0:cambypass(4)=0
		tx$(1)="":tx$(2)="":tx$(3)="":tx$(4)=""
		scrollgo(1)=0:scrollgo(2)=0:scrollgo(3)=0:scrollgo(4)=0
		vholddown=0 : monselected=1
		guigo=1 : textdisplay=0 : showcursor=1 : gameclockstart=1
	ElseIf msg(b,1)="RESETMRU"
		;Reset mru position, make sure mru control, guard vision are enabled, clock start up again, camera move back, stop any bypassed videos
		mrux#=2 : mruz#=-11 : PositionEntity mru,mrux#,mruy#,mruz# : RotateEntity mru,0,0,0 : mrudirection=1
		annx#=16 : annz#=-4 : PositionEntity ann,annx#,anny#,annz#
		ShowEntity mru
		HideEntity mapann
		RotateTexture mrubmp,0
		For m=1 To 4
			If camfunc(m)=4
				tx$(m)=tx$(m)+textscroll$(mrudirection) : scrollgo(m)=1
			EndIf
		Next
		doonce=0
		stopclock=0
		nocontrolmru=0
		mrucaught=0
		guardon=1
		tgscamx=4
		tgscamz=-11
		guigo=1 : textdisplay=0 : showcursor=1
	ElseIf msg(b,1)="FBICONGRATS"
		a=0 : b=464
	ElseIf msg(b,1)="CREDITS"
		a=0 : b=481
	ElseIf msg(b,1)="GAMEOVERSOUND"
		;Play whatever sound is going to be running over the credits
		a=0 : b=b+1
	ElseIf msg(b,1)="CLEARCREDIT"
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,299,640,110,1 : Color 255,255,255 : SetBuffer BackBuffer()
		a=0 : b=b+1
	ElseIf msg(b,1)="RESTARTORQUIT"
		;Wait for either F1 (Restart) or ESC (Quit) and act accordingly
		
	ElseIf msg(b,1)="TEXTSWITCH"
		;SetBuffer ImageBuffer(guilayer) : Cls
		;SetBuffer ImageBuffer(pushlayer) : Cls
		;SetBuffer ImageBuffer(numdisplay) : Cls
		;SetBuffer ImageBuffer(scr_scroll(1)) : Cls
		;SetBuffer ImageBuffer(scr_scroll(2)) : Cls
		;SetBuffer ImageBuffer(scr_scroll(3)) : Cls
		;SetBuffer ImageBuffer(scr_scroll(4)) : Cls
		;SetBuffer ImageBuffer(cursor) : Cls
		;SetBuffer BackBuffer() : Cls
		;stopclock=1
		a=0 : b=b+1
	ElseIf msg(b,1)="GOANN"
		;Hide all the guards
		HideEntity mapguard
		For f=1 To 4
		HideEntity guard(f)
		Next		
		;Set the annihilator off
		anngo=1
		guardon=0
		a=0 : b=b+1
	ElseIf msg(b,1)="CLEARLOWER"
	SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,225,640,300,1 : Color 255,255,255 : SetBuffer BackBuffer() : a=0 : b=b+1 : Delay 500
	Else
		If a>Len(msg(b,0))
		a=0
		If b<linecount
			b=b+1
		EndIf
		EndIf
		a=a+1
		time=MilliSecs()
	EndIf
EndIf

SetBuffer ImageBuffer(textlayer)
	Text 0,msg(b,1),Left$(msg(b,0),a)
SetBuffer BackBuffer()

;Draw the textcursor (the white box)

If msg(b,0) = "" ;Control the X position of the cursor
	If msg(b-1,0)=""
		a=Len(msg(b-2,0)+1)
	Else
		a=Len(msg(b-1,0)+1)
	EndIf
	needtoreseta=1
EndIf

If msg(b,0) <> "" And msg(b,1)<>Yline# ;Keep the cursor at the last Y position it was at
	textcursory#=msg(b,1)
EndIf

textcursorx#=a*13

SetBuffer ImageBuffer(textcursor)
	Color 255,255,255
	Rect textcursorx#+92,textcursory#,13,20
	SetBuffer BackBuffer()
Return

.checkanswer
	If answer$=trueanswer$
		msg(b+1,0)="                                 "
		msg(b+1,0)=msg(b+1,0)+answer$
		answerwrong=0
		a=33
		b=b+1
	Else
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer()
		msg(b+1,0)="INCORRECT RESPONSE"
		answerwrong=1
		a=0
		b=b+1
	EndIf
Return

.cursorcheck
If showcursor=1
;Checking for mouseclicks over the GUI
;buttonpress=0 ; Cancel all button presses first
If MouseDown(1)
If MouseX()>28 And MouseX()<100 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=1 : If monselected<4 : monselected=monselected+1 : Else : monselected=1 : EndIf : mouseclick=1 : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,28,12,28,12,72,13 : SetBuffer BackBuffer()
If MouseX()>28 And MouseX()<100 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=2 : If vholddown<>1 : vholddown=1 : Else : vholddown=0 : EndIf : mouseclick=1 ;V-HOLD
If MouseX()>128 And MouseX()<176 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=3 : If camfunc(monselected)<>1 : If camfunc(monselected)=3 : clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : vtrstopped(monselected)=0 : Gosub normalclock : EndIf : camselected=1 : camfunc(monselected)=1 : tx$(monselected)=tx$(monselected)+textscroll$(8) : scrollgo(monselected)=1 : vholdpos#(monselected)=110 : RotateEntity cam(monselected),camtilt#,0,0 : PositionEntity cam(monselected),camarray(camnum(monselected),0),1,camarray(camnum(monselected),1) : cambypass(monselected)=0 : EndIf : mouseclick=1 : SetBuffer ImageBuffer(pushlayer) : Cls : SetBuffer BackBuffer();CAM - If it's not already set to be a live camera, whatever montior is selected switches to the realtime time variable , shows the mru object and moves the specific camera to the position specified by the chosen channel (1 as default)
If MouseX()>128 And MouseX()<176 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=4 : If camfunc(monselected)<>2 : If camfunc(monselected)=3 : clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : vtrstopped(monselected)=0 : Gosub normalclock : EndIf : camfunc(monselected)=2 : monstate(monselected)=1 : tx$(monselected)=tx$(monselected)+textscroll$(6) : scrollgo(monselected)=1 : Else : If monstate(monselected)<2 : monstate(monselected)=monstate(monselected)+1 : tx$(monselected)=tx$(monselected)+textscroll$(7) : scrollgo(monselected)=1 : Else : monstate(monselected)=1 : tx$(monselected)=tx$(monselected)+textscroll$(6) : scrollgo(monselected)=1 : EndIf : EndIf : vholdpos#(monselected)=110 : RotateEntity cam(monselected),camtilt#,0,0 : PositionEntity cam(monselected),camarray(monnum(monselected),0),1,camarray(monnum(monselected),1) : cambypass(monselected)=0 : mouseclick=1 : SetBuffer ImageBuffer(pushlayer) : Cls : SetBuffer BackBuffer();MON - Realtime again, show mru, but switch to whatever camera the selected monitor is monitoring (changed by repeated clicks to the button).
If MouseX()>192 And MouseX()<240 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=5 : If camfunc(monselected)<>3 : camfunc(monselected)=3 : vholdpos#(monselected)=110 : RotateEntity cam(monselected),camtilt#,0,0 : PositionEntity cam(monselected),camarray(camnum(monselected),0),1,camarray(camnum(monselected),1) : clockspeed(monselected)=0 : tx$(monselected)=tx$(monselected)+textscroll$(9) : scrollgo(monselected)=1 : vtrstopped(monselected)=1 : Gosub clearclock : EndIf : mouseclick=1 : SetBuffer ImageBuffer(pushlayer) : Cls : SetBuffer BackBuffer();VTR - Hide the MRU (it's not in any recordings) and set the time variable to 00:00 - fully definable using the video controls
If MouseX()>192 And MouseX()<240 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=6 : If camfunc(monselected)=3 : If clockspeed(monselected)=2 : If cambypass(monselected)<>1 : cambypass(monselected)=1 : tx$(monselected)=tx$(monselected)+textscroll$(5) : scrollgo(monselected)=1 : Else : cambypass(monselected)=0 : tx$(monselected)=tx$(monselected)+textscroll$(9) : scrollgo(monselected)=1 : EndIf : EndIf : EndIf : mouseclick=1 ;BYP - So long as the video is selected and playing, bypass the video (checked by a seperate routine)
If MouseX()>256 And MouseX()<278 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=7 : If vholddown=1 : vholdspeed#(monselected)=vholdspeed#(monselected)-vholdspeedchange# : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,256,12,256,12,22,13 : SetBuffer BackBuffer() : ElseIf camfunc(monselected)=1 Or camfunc(monselected)=3 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : clockspeed(monselected)=0 : vtrstopped(monselected)=1 : Gosub clearclock : EndIf : vholdpos#(monselected)=110 : If camnum(monselected)>1 : camchange=-1 : camnum(monselected)=camnum(monselected)+camchange : Else : camnum(monselected)=38 : camchange=-1 : EndIf : PositionEntity cam(monselected),camarray(camnum(monselected),0),1,camarray(camnum(monselected),1) : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,256,12,256,12,22,13 : SetBuffer BackBuffer() : EndIf : EndIf : mouseclick=1 : ;MINUS - If v-hold is pressed in... otherwise, if the camera is set to be a live camera...
If MouseX()>282 And MouseX()<304 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=8 : If vholddown=1 : vholdspeed#(monselected)=vholdspeed#(monselected)+vholdspeedchange# : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,282,12,282,12,22,13 : SetBuffer BackBuffer() : ElseIf camfunc(monselected)=1 Or camfunc(monselected)=3 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : clockspeed(monselected)=0 : vtrstopped(monselected)=1 : Gosub clearclock : EndIf : vholdpos#(monselected)=110 : If camnum(monselected)<38 : camchange=1 : camnum(monselected)=camnum(monselected)+camchange : Else : camnum(monselected)=1 : camchange=1 : EndIf : PositionEntity cam(monselected),camarray(camnum(monselected),0),1,camarray(camnum(monselected),1) : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,282,12,282,12,22,13 : SetBuffer BackBuffer() : EndIf : EndIf : mouseclick=1 : ;PLUS
If MouseX()>256 And MouseX()<304 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=9 : If camfunc(monselected)<>4 : If camfunc(monselected)=3 : clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : vtrstopped(monselected)=0 : Gosub normalclock : EndIf : camfunc(monselected)=4 : cambypass(monselected)=0 : RotateEntity cam(monselected),90,0,0 : PositionEntity cam(monselected),tgscamx,tgscamy,tgscamz : vholdpos#(monselected)=110 : tx$(monselected)=tx$(monselected)+textscroll$(mrudirection) : scrollgo(monselected)=1 : EndIf : mouseclick=1 ;TGS

If MouseX()>364 And MouseX()<388 And MouseY()>433 And MouseY()<446 And nocontrolmru=0 And mouseclick=0 ;FORWARDS
	buttonpress=10
	If mruperformaction=0
			If mrumovez=0 And mrudirection=1 ; North
				posneg=1 : Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=2 ; East
				posneg=1 : Gosub moveX
			EndIf
			If mrumovez=0 And mrudirection=3 ; South
				posneg=-1 : Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=4 ; West
				posneg=-1 : Gosub moveX
			EndIf
	EndIf
	SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,364,12,364,12,24,13 : SetBuffer BackBuffer()
	;mouseclick=1
EndIf
If MouseX()>332 And MouseX()<356 And MouseY()>444 And MouseY()<457 And nocontrolmru=0 And mouseclick=0 ;LEFT
	buttonpress=11
	If mruperformaction=0
			If mruturnl=0
				mrudirection=mrudirection-1
				If mrudirection<1 Then mrudirection=4
				mruturntarget#=90
				turntarget#=EntityYaw#(mru)+90
				mruturnl=1
				For m=1 To 4
				If camfunc(m)=4
					tx$(m)=tx$(m)+textscroll$(mrudirection) : scrollgo(m)=1
				EndIf
				Next
			EndIf
	EndIf
	SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,332,23,332,23,24,13 : SetBuffer BackBuffer()
	checkdirection=0
	;mouseclick=1
EndIf
If MouseX()>396 And MouseX()<420 And MouseY()>444 And MouseY()<457 And nocontrolmru=0 And mouseclick=0 ;RIGHT
	buttonpress=12
	If mruperformaction=0
			If mruturnr=0
				mrudirection=mrudirection+1
				If mrudirection>4 Then mrudirection=1
				mruturntarget#=-90
				turntarget#=EntityYaw#(mru)-90
				mruturnr=1
				For m=1 To 4
				If camfunc(m)=4
					tx$(m)=tx$(m)+textscroll$(mrudirection) : scrollgo(m)=1
				EndIf
				Next
			EndIf
	EndIf
	SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,396,23,396,23,24,13 : SetBuffer BackBuffer()
	checkdirection=0
	;mouseclick=1
EndIf
If MouseX()>364 And MouseX()<388 And MouseY()>455 And MouseY()<468 And nocontrolmru=0 And mouseclick=0 ;BACKWARDS
	buttonpress=13
	If mruperformaction=0	
			If mrumovez=0 And mrudirection=1 ; North
				posneg=-1 : Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=2 ; East
				posneg=-1 : Gosub moveX
			EndIf
			If mrumovez=0 And mrudirection=3 ; South
				posneg=1 : Gosub moveZ
			EndIf
			If mrumovex=0 And mrudirection=4 ; West
				posneg=1 : Gosub moveX
			EndIf
	EndIf
	SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,364,34,364,34,24,13 : SetBuffer BackBuffer()
	;mouseclick=1
EndIf

	;Making sure the MRU direction isn't invalid
	If mrudirection<1 Then mrudirection=4
	If mrudirection>4 Then mrudirection=1

If MouseX()>464 And MouseX()<496 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=14 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : If vtrstopped(monselected)=0 : If clockspeed(monselected)=0 : clockspeed(monselected)=-4 : Else : clocksecspeed(monselected)=2 : clockspeed(monselected)=rwspeed : EndIf : ElseIf vtrstopped(monselected)=1 : If vtrrwff(monselected)=0 : vtrrwff(monselected)=1 : clocksecspeed(monselected)=4 : clockspeed(monselected)=-60 : ElseIf vtrrwff(monselected)=1 : vtrrwff(monselected)=0 : clocksecspeed(monselected)=1 : clockspeed(monselected)=0 : EndIf : EndIf : EndIf : EndIf : mouseclick=1 ;<< - rewind the tape
If MouseX()>512 And MouseX()<576 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=15 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : milli(monselected)=milli(0) : vtrstopped(monselected)=0 : vtrrwff(monselected)=0 : EndIf : EndIf : mouseclick=1 ;PLAY - if the video has been stopped, set it going again.
If MouseX()>592 And MouseX()<624 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=16 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : If vtrstopped(monselected)=0 : If clockspeed(monselected)=0 : clockspeed(monselected)=4 : Else : clocksecspeed(monselected)=2 : clockspeed(monselected)=ffspeed : EndIf : ElseIf vtrstopped(monselected)=1 : If vtrrwff(monselected)=0 : vtrrwff(monselected)=2 : clocksecspeed(monselected)=4 : clockspeed(monselected)=60 : ElseIf vtrrwff(monselected)=2 : vtrrwff(monselected)=0 : clocksecspeed(monselected)=1 : clockspeed(monselected)=0 : EndIf : EndIf : EndIf : EndIf : mouseclick=1 ;>> - fastforward the tape
If MouseX()>464 And MouseX()<528 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=17 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : clocksecspeed(monselected)=1 : clockspeed(monselected)=0 : vtrstopped(monselected)=1 : vtrrwff(monselected)=0 : EndIf : EndIf : mouseclick=1 ;STOP - set the time to 00:00 and speed to 0
If MouseX()>544 And MouseX()<624 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=18 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : If vtrstopped(monselected)<>1 : If clockspeed(monselected)<>0 : clockspeed(monselected)=0 : Else : clockspeed(monselected)=2 : milli(monselected)=milli(0) : EndIf : EndIf : EndIf : EndIf : mouseclick=1 ;PAUSE - set the clockspeed of the specific clock to 0, and back to 2 when unclicked
Else
	mouseclick=0
	buttonpress=0
	SetBuffer ImageBuffer(pushlayer) : Cls : SetBuffer BackBuffer()
	camchange=1
EndIf
;For cancelling held down buttons (FF, RW, etc...)
If clockspeed(monselected)=ffspeed And MouseDown(1)=False Then clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : milli(monselected)=milli(0)
If clockspeed(monselected)=rwspeed And MouseDown(1)=False Then clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : milli(monselected)=milli(0)
If clockspeed(monselected)=-4 And MouseDown(1)=False Then clockspeed(monselected)=0
If clockspeed(monselected)=4 And MouseDown(1)=False Then clockspeed(monselected)=0

;Drawing graphics for buttons that can be toggled on/off
If vholddown=1 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,28,34,28,34,72,13 : SetBuffer BackBuffer()
If camfunc(monselected)=1 : SetBuffer ImageBuffer(pushlayer) : Color 0,0,0 : Rect 128,12,112,35 : Rect 256,34,48,13 : Rect 464,12,160,35 : Color 255,255,255 : DrawBlockRect guipush,128,12,128,12,48,13 : SetBuffer BackBuffer()
ElseIf camfunc(monselected)=2 : SetBuffer ImageBuffer(pushlayer) : Color 0,0,0 : Rect 128,12,112,35 : Rect 256,34,48,13 : Rect 464,12,160,35 : Color 255,255,255 : DrawBlockRect guipush,128,34,128,34,48,13 : SetBuffer BackBuffer()
ElseIf camfunc(monselected)=3 : SetBuffer ImageBuffer(pushlayer) : Color 0,0,0 : Rect 128,12,112,35 : Rect 256,34,48,13 : Rect 464,12,160,35 : Color 255,255,255 : DrawBlockRect guipush,192,12,192,12,48,13 : SetBuffer BackBuffer()
ElseIf camfunc(monselected)=4 : SetBuffer ImageBuffer(pushlayer) : Color 0,0,0 : Rect 128,12,112,35 : Rect 256,34,48,13 : Rect 464,12,160,35 : Color 255,255,255 : DrawBlockRect guipush,256,34,256,34,48,13 : SetBuffer BackBuffer()
EndIf
If cambypass(monselected)=1 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,192,34,192,34,48,13 : SetBuffer BackBuffer()
If camfunc(monselected)=3
	;SetBuffer ImageBuffer(pushlayer) : Color 0,0,0 : Rect 464,12,160,35 : Color 255,255,255 : SetBuffer BackBuffer()
	If clockspeed(monselected)=0
		If vtrstopped(monselected)=1 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,464,34,464,34,64,13 : SetBuffer BackBuffer() Else SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,544,34,544,34,79,13 : SetBuffer BackBuffer()
	EndIf
	If clockspeed(monselected)=2 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,512,12,512,12,64,13 : SetBuffer BackBuffer()
	If clockspeed(monselected)=4 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,592,12,592,12,32,13 : DrawBlockRect guipush,544,34,544,34,79,13 : SetBuffer BackBuffer()
	If clockspeed(monselected)=40 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,592,12,592,12,32,13 : DrawBlockRect guipush,512,12,512,12,64,13 : SetBuffer BackBuffer()
	If clockspeed(monselected)=-4 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,464,12,464,12,32,13 : DrawBlockRect guipush,544,34,544,34,79,13 : SetBuffer BackBuffer()
	If clockspeed(monselected)=-40 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,464,12,464,12,32,13 : DrawBlockRect guipush,512,12,512,12,64,13 : SetBuffer BackBuffer()
	If clockspeed(monselected)=60 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,464,34,464,34,64,13 : DrawBlockRect guipush,592,12,592,12,32,13 : SetBuffer BackBuffer()
	If clockspeed(monselected)=-60 Then SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,464,34,464,34,64,13 : DrawBlockRect guipush,464,12,464,12,32,13 : SetBuffer BackBuffer()
EndIf

;Making sure the same VTR channel can't be chosen by 2 at the same time

	If camfunc(monselected)=3
		If monselected=1
			If camfunc(2)=3 : If camnum(monselected)=camnum(2) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(3)=3 : If camnum(monselected)=camnum(3) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(4)=3 : If camnum(monselected)=camnum(4) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
		ElseIf monselected=2
			If camfunc(1)=3 : If camnum(monselected)=camnum(1) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(3)=3 : If camnum(monselected)=camnum(3) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(4)=3 : If camnum(monselected)=camnum(4) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
		ElseIf monselected=3
			If camfunc(1)=3 : If camnum(monselected)=camnum(1) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(2)=3 : If camnum(monselected)=camnum(2) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(4)=3 : If camnum(monselected)=camnum(4) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
		ElseIf monselected=4
			If camfunc(1)=3 : If camnum(monselected)=camnum(1) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(2)=3 : If camnum(monselected)=camnum(2) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
			If camfunc(3)=3 : If camnum(monselected)=camnum(3) : camnum(monselected)=camnum(monselected)+camchange : EndIf : EndIf
		EndIf		
	EndIf
	If camnum(monselected)=0 Then camnum(monselected)=38
	If camnum(monselected)=39 Then camnum(monselected)=1

DrawImage cursor,MouseX()-7,MouseY()-2
EndIf ;Showcursor=1
Return

.clearclock
mins(monselected)=0
secs(monselected)=0
milli(monselected)=0
completesecs(monselected)=0
fakemins(monselected)=0
secss$(monselected)="00"
minss$(monselected)="00"
millicount(monselected)=MilliSecs()
Return

.normalclock
mins(monselected)=mins(0)
secs(monselected)=secs(0)
milli(monselected)=milli(0)
completesecs(monselected)=completesecs(0)
fakemins(monselected)=fakemins(0)
secss$(monselected)=secss$(0)
minss$(monselected)=minss$(0)
millicount(monselected)=millicount(0)
Return

.moveX
	mrutarget#=EntityX#(mru)+(mrumove#*posneg)
	mrutargetx#=mrumove#
	mrumovex=1
	checkfbipos=0
	Gosub collisioncheckx
Return

.moveZ
	mrutarget#=EntityZ#(mru)+(mrumove#*posneg)
	mrutargetz#=mrumove#
	mrumovez=1
	checkfbipos=0
	Gosub collisioncheckz
Return

.collisioncheckx
	If walkability(mrutarget#,EntityZ#(mru)*-1) = -16777216
		mrutarget#=EntityX#(mru)
		mrutargetx#=0
		mrumovex=0
		checkfbipos=1
	EndIf
Return

.collisioncheckz
	If walkability(EntityX#(mru),mrutarget#*-1) = -16777216
		mrutarget#=EntityZ#(mru)
		mrutargetz#=0
		mrumovez=0
		checkfbipos=1
	EndIf
Return

.mrucaught
	SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer()
	mrucaught=1
	anngo=0
	alarmtime=MilliSecs()
	destmru=1
Return

.stopMRU
	If doonce=0
		nocontrolmru=1
		anntimecount=MilliSecs()
		doonce=1
	EndIf
Return

.SetSpeedFactor ;For the framelimiter
   FL\CurrentTicks = MilliSecs()
   FL\SpeedFactor = (FL\CurrentTicks - FL\FrameDelay) / (FL\TicksPerSecond / FL\TargetFPS)
   If FL\SpeedFactor <= 0 Then FL\SpeedFactor = 0.00000000001    
   FL\FPS = FL\TargetFPS / FL\SpeedFactor    
   FL\FrameDelay = FL\CurrentTicks
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
;------------
;Frame Limiter:
;------------
Function FrameLimitInit(target_FPS#)
	FL\TargetFPS# = target_FPS#
	FL\TicksPerSecond = 1000 	
	FL\FrameDelay = MilliSecs()
End Function