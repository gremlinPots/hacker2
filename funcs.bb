;FUNCTIONS
;------------
;Floor Pieces:
;------------
Function CreateFloor(xpos#,zpos#,x#,z#); For creating... floors (and flat sprites)
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

Function CreateFront(xpos#,zpos#,x#); For creating the walls facing the camera (X axis)
sprite=CreateMesh()
he=CreateBrush(255,255,255)
v=CreateSurface(sprite,he)
FreeBrush he
AddVertex ( v,xpos#,3,zpos#,     0,0)
AddVertex ( v,xpos#+x#,3,zpos#,  1,0)
AddVertex ( v,xpos#+x#,0,zpos#,  1,1)
AddVertex ( v,xpos#,0,zpos#,     0,1)
AddTriangle( v,0,1,2)
AddTriangle( v,2,3,0)
Return sprite
End Function

Function CreateDoor(xpos#,zpos#,x#,z#,doorstart#,doorwidth#,doorvar); For creating the walls with doors in them (Z axis)
wallheight#=3
doorheight#=1.3
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
;------------
;Frame Limiter:
;------------
Function FrameLimitInit(target_FPS#)
	FL\TargetFPS# = target_FPS#
	FL\TicksPerSecond = 1000 	
	FL\FrameDelay = MilliSecs()
End Function