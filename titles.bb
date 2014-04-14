titlecamera=CreateCamera()
titlelight=CreateLight()

CameraClsMode titlecamera, False, True

BLURFACTOR# = 0.9
mdlBlur = CreateCube(titlecamera)
EntityAlpha mdlBlur, 1 - BLURFACTOR#
ScaleEntity mdlBlur, 500, 500, 500
FlipMesh mdlBlur
EntityColor mdlBlur, 0, 0, 0

PositionEntity titlecamera,500,2,500
RotateEntity titlecamera,90,0,0

ClearTextureFilters

splashtex=LoadTexture("splash.png",1)

plane=CreateFloor(-2,1.5,4,3)
plane2=CreateFloor(-2,1.5,4,3)

planex#=500
planey#=-50
planez#=500
planealpha#=0

plane2alpha#=0

EntityAlpha plane,planealpha#
PositionEntity plane,planex#,planey#,planez#

EntityAlpha plane2,planealpha#
PositionEntity plane2,planex#,0,planez#

EntityTexture plane,splashtex
EntityFX plane,1

EntityColor plane2,0,0,170
EntityFX plane2,1

FreeTexture splashtex
FreeTexture bluescreentex

;---------------------
;Zoom In
;---------------------
While Not planey#>=0
planey#=planey#+0.2
planealpha#=(planey#+50)/50
PositionEntity plane,planex#,planey#,planez#
RotateEntity plane,0,planey#*50,0
EntityAlpha plane,planealpha#
RenderWorld
Flip
Wend
;---------------------
;Wait
;---------------------
sec5count#=MilliSecs()

;Include "variablesetup.bb" ;Create all arrays and variables, load textures, etc... If this takes less than 5 seconds, the loop below will run for the remainder. If it takes more, the loop will exit immediatly! 

While MilliSecs()<sec5count#+5500

;Wait 5 seconds

PositionEntity plane,planex,planey,planez

RenderWorld

Flip

Wend
;---------------------
;Zoom Out
;---------------------
While Not planey#<=-300
falldist#=falldist#+0.01
planey#=planey#-falldist#
planealpha#=(planey#+300)/300
PositionEntity plane,planex#,planey#,planez#
EntityAlpha plane,planealpha#
RenderWorld
Flip
Wend

;---------------------
;Fade Blue screen in
;---------------------
While Not plane2alpha#>=1
plane2alpha#=plane2alpha#+0.02
EntityAlpha plane2,plane2alpha#
RenderWorld
Flip
Wend

HideEntity plane
HideEntity plane2