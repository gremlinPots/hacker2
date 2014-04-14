Graphics3D 640,480,32,2
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
Dim millicount(4):millicount(0)=MilliSecs():millicount(1)=MilliSecs():millicount(2)=MilliSecs():millicount(3)=MilliSecs():millicount(4)=MilliSecs()
Dim clockspeed(4):clockspeed(0)=2:clockspeed(1)=2:clockspeed(2)=2:clockspeed(3)=2:clockspeed(4)=2
Dim clocksecspeed(4):clocksecspeed(0)=1:clocksecspeed(1)=1:clocksecspeed(2)=1:clocksecspeed(3)=1:clocksecspeed(4)=1
Dim vtrstopped(4):vtrstopped(1)=0:vtrstopped(2)=0:vtrstopped(3)=0:vtrstopped(4)=0
Dim vtrrwff(4):vtrrwff(1)=0:vtrrwff(2)=0:vtrrwff(3)=0:vtrrwff(4)=0 ;vtrffrw signifies whether or not the fast forward/rewind button have been pushed in. 0=none 1=rewind 2=fastforward
rwspeed=-40:ffspeed=40



timecheck=MilliSecs()
secmonb=31

;Vhold variables (needs to be 1 for each viewport) - in arrays, again

Dim vholdpos#(4):vholdpos#(1)=0:vholdpos#(2)=0:vholdpos#(3)=0:vholdpos#(4)=0
Dim vholdspeed#(4):vholdspeed#(1)=Int(Rnd(-8,10))*4:vholdspeed#(2)=Int(Rnd(-8,10))*4:vholdspeed#(3)=Int(Rnd(-8,10))*4:vholdspeed#(4)=Int(Rnd(-8,10))*4

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

guilayer=LoadImage("gui.png") ;Load the GUI graphic
MaskImage guilayer,0,0,255
guipush=LoadImage("guipush.png")
pushlayer=CreateImage(640,59)

numdisplay=CreateImage(640,480)

cursor=LoadImage("cursor.png") ;Load the cursor, set transparancy and hide the windows pointer
MaskImage cursor,0,17,119
HidePointer

;Text variables and bits
textbackground=CreateImage(640,480)
SetBuffer ImageBuffer(textbackground)
Color 0,0,170
Rect 0,0,640,480
Color 255,255,255
SetBuffer BackBuffer()
textlayer=CreateImage(640,480)

textleftoffset=30 ;pixel gap to leave between the left margin (multiple of 10)

;Images that will be used in the text
mrupic=LoadImage("mrupic.bmp")
mfsmpic=LoadImage("mfsmpic.bmp")
;Text variables and actual game text
fntcourier=LoadFont ("Courier",20,True,False,False) 
SetFont fntcourier
normaltypepause=90
quicktypepause=30
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
EntityTexture tgsmap,map
PositionEntity tgsmap,0.5,mapy#,-0.5

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
mrumovespeed#=0.04;0.01
mruturnspeed#=4;1

;HideEntity mru

mruperfromaction=0

;Temporary Annihilator object
ann=CreateCube()
EntityColor ann,255,0,0
annx#=16
annz#=-4
ScaleEntity ann,0.5,1,0.5
PositionEntity ann,annx#,1,annz#
annmovespeed#=0.052

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

;Freeing all images previously loaded
FreeImage bmp

;Including external bb files
Include "camarray.bb"
Include "aStarLibrary.bb"
Include "textvar.bb"


;Initialising the game
guidisplay=0
textdisplay=1
showcursor=0
a=0 : b=185

typepause=quicktypepause

;---------------------
;Game Loop
;---------------------
While Not KeyDown(1)


;The timer(s)
If stopclock=0 And mruspotted=0 ;added mruspotted=0 to make sure spotting events wouldn't cause an endless loop when the time still matched
For t=0 To 4
;Update the clock(s) * 5
If MilliSecs() > millicount(t) + 10

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
	
	If secs(t) < 10 : secss$(t)="0"+secs(t) : Else : secss$(t)=secs(t) : EndIf
	If fakemins(t) < 10 : minss$(t)="0"+fakemins(t) : Else : minss$(t)=fakemins(t) : EndIf
	millicount(t)=MilliSecs()
EndIf ;If MilliSecs() > millicount(t) + 10
Next
EndIf ;If stopclock=0


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


;Find positions for the TGS camera
If Int(EntityX#(mru))>=4 And Int(EntityX#(mru))<=35 And Int(EntityZ#(mru))<=-4 And Int(EntityZ#(mru))>=-22
	tgscamx=Int(EntityX#(mru))
	tgscamy=mapy#+6.5
	tgscamz=Int(EntityZ#(mru))
EndIf

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
	For v=1 To 4
		If camfunc(v)=2 Then vholdpos#(v)=110
	Next
EndIf


If mruspotted=1
;Only set off alarm once MRU has reached it's target.
	If mrumovez=0 And mrumovex=0
		;Stop the clocks
		;Play Suren
		stopclock=1
		;Pause for x seconds while you play the siren
		If MilliSecs()>=alarmtime+3000 ;if three seconds have passed
		;Stop Siren
		;Hide all the guards
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


If guigo=1 And textdisplay=0

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
	
;Checking for bypassed cameras and seeing if the timecode matches
	If cambypass(i)=1
		If camnum(i)=secmona Or camnum(i)=secmonb ;if the bypassed camera num equals a currently monitored camera
		;If one of the monitors is looking at that camera, set it's time to the bypassed camera's time
		For xi=1 To 4
			If camfunc(xi)=2

				
mins(xi)=mins(i)
secs(xi)=secs(i)
milli(xi)=milli(i)
completesecs(xi)=completesecs(i)
fakemins(xi)=fakemins(i)
secss$(xi)=secss$(i)
minss$(xi)=minss$(i)
millicount(xi)=millicount(i)
				

			EndIf ;if camfunc(xi)=3
		Next
		
		;Finally, check to see if an alarm needs triggering
		;If stopclock=0 ;only check if the clock is not stopped (ie, if it's playing)
		;	If secs(i)<>secs(0) Or mins(i)<>mins(0) ;If a timecode error IS found
		;		mruspotted=1 : whosawmru=3 : alarmtime=MilliSecs()
		;	EndIf
		;EndIf ;If stopclock=0
		EndIf ;If camnum(i)=secmona or camnum(i)=secmonb
	EndIf

;Various vholdspeed# and vholdpos# operations
	If vholdspeed#(i) > 40
		vholdspeed#(i) = -32
	ElseIf vholdspeed#(i) < -32
		vholdspeed#(i) = 40
	EndIf
	If vholdspeed#(i) <> 0
		vholdpos#(i)=vholdpos#(i)+vholdspeed#(i)
	Else
		If vholdpos#(i) <> 0
			If vholdpos#(i) >= 70 Then vholdpos#(i)=vholdpos#(i)+2 Else vholdpos#(i)=vholdpos#(i)-2
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
	If camfunc(j)=3 ;If the camera is switched to video mode, hide the mru
		HideEntity mru
		HideEntity ann
	Else
		ShowEntity mru
		ShowEntity ann
	EndIf
	
	If anngo<>1 Then ShowEntity guard(i)
	
	If camfunc(i)<>4
	SetBuffer ImageBuffer(port(i)) ;Video effects - Scanlines and timestamp
	Color 0,0,0 : For p=1 To 140 Step 2 : Rect 0,p,212,1 : Next ;Scanlines
	If clockspeed(i)=ffspeed Or clockspeed(i)=rwspeed ;Draw the FF/RW lines on screen
		DrawBlockRect wholestatic,0,35,Rand(88),Rand(160),212,7
		DrawBlockRect wholestatic,0,97,Rand(88),Rand(160),212,7
	ElseIf clockspeed(i)=4 Or clockspeed(i)=-4
		DrawBlockRect wholestatic,0,35,Rand(88),Rand(160),212,7
	EndIf
	If clockspeed(i)=0 And vtrstopped(i)<>1 ;Draw PAUSE line
		DrawBlockRect wholestatic,0,35,Rand(88),Rand(160),212,7
	EndIf
	If vtrstopped(i)=1 ;if the video screen is stopped, blank the port image out
		Color 100,100,100 : Rect 0,0,212,140
	EndIf
	Color 0,0,0 : Rect 49,120,114,20 ;black text background
	Color 255,255,255 : Text 85,125,minss$(i)+":"+secss$(i) ;white timestamp
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
	;Text scroller operations (sharing the 1 to 4 loop of another operation)
	If scrollgo(a)=1
		go#(a)=go#(a)+1.5
		If go#(a)>8
			SetBuffer ImageBuffer(scr_scroll(a))
			go#(a)=0
			Color 255,0,0 : Text 229,0,Mid$(tx$(a),tx_pos(a),1) : Color 255,255,255
			tx_pos(a)=tx_pos(a)+1 : If tx_pos(a) > Len(tx$(a)) Then tx_pos(a)=1 : scrollgo(a)=0 : tx$(a)=""
			SetBuffer BackBuffer()
		EndIf
		SetBuffer ImageBuffer(scr_scroll(a))
		DrawBlock scr_scroll(a),-2,0
		SetBuffer BackBuffer()
	EndIf
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
	
	
	;Flash the selected monitor number on and off
	If MilliSecs() > flasher+500 : If flashon=0 : flashon=1 : ElseIf flashon=1 : flashon=0 : EndIf : flasher=MilliSecs() : EndIf
	If flashon=1
	SetBuffer ImageBuffer(numdisplay)
	Color 0,0,0 : Rect numxcoord(monselected),numycoord(monselected),30,20 : Color 255,255,255
	SetBuffer BackBuffer()
	EndIf
	
	Gosub cursorcheck
	
	DrawImage guilayer,0,0
	DrawImage pushlayer,0,421
	
	DrawImage scr_scroll(1),64,177
	DrawImage scr_scroll(2),384,177
	DrawImage scr_scroll(3),64,380
	DrawImage scr_scroll(4),384,380
	
	DrawImage numdisplay,0,0

	DrawImage cursor,MouseX()-7,MouseY()-2

	;Text 0,0,"MONITOR: "+monselected
	;Text 0,20,"V-HOLD: "+vholddown
	;Text 0,40,""+mouseclick
	;Text 0,40,"FUNCTION: "+camfunc(monselected)
	;Text 0,60,"CAMERA NUM: "+camnum(monselected)
	;Text 0,80,"MONTOR NUM: "+monnum(monselected)
	;Text 0,100,"BYPASSED?: "+cambypass(monselected)
	;Text 0,120,clockspeed(monselected)
	;Text 0,140,vtrrwff(monselected)
	Text 0,0,camnum(monselected)
	Text 0,20,mrudirection
	
	Flip
	
	;RenderWorld ;RenderWorld is now called 'higher up' the code, when the cameras are put in their proper positions
Next

EndIf ;guigo=1

;-------------

If guigo=0 And textdisplay=1
	
	Gosub printtext

	DrawImage textbackground,0,0
	DrawImage textlayer,textleftoffset-2,0
	
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

If MilliSecs()>time+typepause
	If needtoreseta=1 : a=0 : needtoreseta=0 : EndIf
	If msg(b,1)="CLS"
		FlushKeys
		SetBuffer ImageBuffer(textlayer) : Cls : SetBuffer BackBuffer() : a=0 : b=b+1 : Delay 500
	ElseIf msg(b,1)="DIAL"
		pausetime#=5000+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset-2,0 : Gosub cursorcheck : Flip
		Wend
		a=0 : b=b+1 ;Play the dialing sounds
	ElseIf msg(b,1)="GOBACK"
		pausetime#=1000+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset-2,0 : Gosub cursorcheck : Flip
		Wend
		SetBuffer ImageBuffer(textlayer) : Color 0,0,0 : Rect 0,msg(b-1,1),640,20,1 : Color 255,255,255 : SetBuffer BackBuffer() : a=0 : b=b-3
	ElseIf Left$(msg(b,1),3)="PAU"
		pausetime# = Right$(msg(b,1),1) : pausetime#=(pausetime#*1000)+MilliSecs()
		While MilliSecs() <= pausetime#
			DrawImage textbackground,0,0 : DrawImage textlayer,textleftoffset-2,0 : Gosub cursorcheck : Flip
		Wend
		;Delay (pausetime#*1000)
		b=b+1
	ElseIf msg(b,1)="LOGON"
		Print ""
		Print ""
		Print ""
		Print ""
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		logonname$=Input(String$(" ",(textleftoffset/10)-1)+"LOGON PLEASE: ")
		;ShowEntity textcursor
		logonname$=Upper$(logonname$)
		typepause=quicktypepause
		msg(8,0)=msg(8,0)+logonname$
		a=14
		b=b+1
	ElseIf msg(b,1)="Q2"
		For l=1 To 12
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"INPUT FROM SURVEILLANCE CAMERAS: ")
		;ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="2"
		Gosub checkanswer
	ElseIf msg(b,1)="Q3"
		For l=1 To 13
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"INPUT FROM PRE-RECORDED TAPES:   ")
		;ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="3"
		Gosub checkanswer
	ElseIf msg(b,1)="Q6"
		For l=1 To 14
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"CAMERA BYPASS SWITCH:            ")
		;ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="6"
		Gosub checkanswer
	ElseIf msg(b,1)="Q4"
		For l=1 To 15
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"OUTPUT TO SECURITY MONITORS:     ")
		;ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="4"
		Gosub checkanswer
	ElseIf msg(b,1)="Q1"
		For l=1 To 16
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"INPUT FROM MFSM PANEL CONTROLS:  ")
		;ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="1"
		Gosub checkanswer
	ElseIf msg(b,1)="Q7"
		For l=1 To 17
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"PHNORDMAN VIDEO MATRIX (PVM):    ")
		;ShowEntity textcursor
		answer$=Upper$(answer$)
		trueanswer$="7"
		Gosub checkanswer
	ElseIf msg(b,1)="Q5"
		For l=1 To 18
			Print ""
		Next
		;HideEntity textcursor
		DrawImage textlayer,textleftoffset-2,0 : Flip
		answer$=Input(String$(" ",(textleftoffset/10)-1)+"OUTPUT TO MFSM DISPLAY:          ")
		;ShowEntity textcursor
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
		If logonname$="00987" Then a=0 : b=171
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
	ElseIf msg(b,1)="MRUPIC"
		;HideEntity textcursor
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textlayer)
			DrawBlockRect mrupic,textleftoffset,140,0,0,500,picnum ;Image is 500 X 90 pixels
			SetBuffer BackBuffer()
			DrawImage textlayer,textleftoffset-2,0 : Flip
		Until picnum=90
		a=0 : b=b+1
		;ShowEntity textcursor
	ElseIf msg(b,1)="MFSMPIC"
		;HideEntity textcursor
		SetBuffer ImageBuffer(textbackground)
		Color 136,136,136
		Rect 0,0,640,480
		Color 255,255,255
		SetBuffer BackBuffer()
		DrawImage textbackground,0,0
		DrawImage textlayer,textleftoffset-2,0
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textlayer)
			DrawBlockRect mfsmpic,textleftoffset-10,10,0,0,532,picnum ;Image is 532 X 214 pixels
			SetBuffer BackBuffer()
			DrawImage textlayer,textleftoffset-2,0 : Flip
		Until picnum=214
		a=0 : b=b+1
		;ShowEntity textcursor
	ElseIf msg(b,1)="TEXTBGGREY"
		SetBuffer ImageBuffer(textbackground)
		Color 136,136,136
		Rect 0,0,640,480
		Color 255,255,255
		SetBuffer BackBuffer()
		a=0 : b=b+1
	ElseIf msg(b,1)="GUIPIC"
		;HideEntity textcursor
		picnum=0
		Repeat
		picnum=picnum+1
			SetBuffer ImageBuffer(textbackground)
			DrawBlockRect guilayer,0,421,0,421,640,picnum
			SetBuffer BackBuffer()
			DrawImage textbackground,0,0 : Flip
		Until picnum=59
		showcursor=1
		a=0 : b=b+1
		;ShowEntity textcursor
	ElseIf msg(b,1)="WAITRETURN"	
		Repeat
		Until KeyDown(28)=True
		a=0 : b=b+1
		FlushKeys
	ElseIf msg(b,1)="GUISWITCH"
		guigo=1 : textdisplay=0 : showcursor=1 ;: monselected=1
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
		For f=1 To 4
		HideEntity guard(f)
		Next		
		;Set the annihilator off
		anngo=1
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
If MouseX()>256 And MouseX()<278 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=7 : If vholddown=1 : vholdspeed#(monselected)=vholdspeed#(monselected)-4 : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,256,12,256,12,22,13 : SetBuffer BackBuffer() : ElseIf camfunc(monselected)=1 Or camfunc(monselected)=3 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : clockspeed(monselected)=0 : vtrstopped(monselected)=1 : Gosub clearclock : EndIf : vholdpos#(monselected)=110 : If camnum(monselected)>1 : camchange=-1 : camnum(monselected)=camnum(monselected)+camchange : Else : camnum(monselected)=38 : camchange=-1 : EndIf : PositionEntity cam(monselected),camarray(camnum(monselected),0),1,camarray(camnum(monselected),1) : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,256,12,256,12,22,13 : SetBuffer BackBuffer() : EndIf : EndIf : mouseclick=1 : ;MINUS - If v-hold is pressed in... otherwise, if the camera is set to be a live camera...
If MouseX()>282 And MouseX()<304 And MouseY()>433 And MouseY()<446 And mouseclick=0 Then buttonpress=8 : If vholddown=1 : vholdspeed#(monselected)=vholdspeed#(monselected)+4 : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,282,12,282,12,22,13 : SetBuffer BackBuffer() : ElseIf camfunc(monselected)=1 Or camfunc(monselected)=3 : If cambypass(monselected)<>1 : If camfunc(monselected)=3 : clockspeed(monselected)=0 : vtrstopped(monselected)=1 : Gosub clearclock : EndIf : vholdpos#(monselected)=110 : If camnum(monselected)<38 : camchange=1 : camnum(monselected)=camnum(monselected)+camchange : Else : camnum(monselected)=1 : camchange=1 : EndIf : PositionEntity cam(monselected),camarray(camnum(monselected),0),1,camarray(camnum(monselected),1) : SetBuffer ImageBuffer(pushlayer) : DrawBlockRect guipush,282,12,282,12,22,13 : SetBuffer BackBuffer() : EndIf : EndIf : mouseclick=1 : ;PLUS
If MouseX()>256 And MouseX()<304 And MouseY()>455 And MouseY()<468 And mouseclick=0 Then buttonpress=9 : If camfunc(monselected)<>4 : If camfunc(monselected)=3 : clocksecspeed(monselected)=1 : clockspeed(monselected)=2 : vtrstopped(monselected)=0 : Gosub normalclock : EndIf : camfunc(monselected)=4 : cambypass(monselected)=0 : RotateEntity cam(monselected),90,0,0 : PositionEntity cam(monselected),tgscamx,tgscamy,tgscamz : vholdpos#(monselected)=110 : tx$(monselected)=tx$(monselected)+textscroll$(mrudirection) : scrollgo(monselected)=1 : EndIf : mouseclick=1 ;TGS

If MouseX()>364 And MouseX()<388 And MouseY()>433 And MouseY()<446 And mouseclick=0 ;FORWARDS
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
If MouseX()>332 And MouseX()<356 And MouseY()>444 And MouseY()<457 And mouseclick=0 ;LEFT
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
	;mouseclick=1
EndIf
If MouseX()>396 And MouseX()<420 And MouseY()>444 And MouseY()<457 And mouseclick=0 ;RIGHT
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
	;mouseclick=1
EndIf
If MouseX()>364 And MouseX()<388 And MouseY()>455 And MouseY()<468 And mouseclick=0 ;BACKWARDS
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