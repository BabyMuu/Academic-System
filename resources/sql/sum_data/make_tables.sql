USE [SchoolManagerSystem]
GO
/****** Object:  Table [dbo].[teacher]    Script Date: 09/21/2021 22:18:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[teacher](
	[teaNum] [char](8) NOT NULL,
	[teaName] [varchar](20) NOT NULL,
	[teaTitle] [varchar](20) NOT NULL,
	[teaTypeId] [varchar](20) NOT NULL,
	[teaBtd] [datetime] NOT NULL,
	[pwd] [varchar](20) NOT NULL,
	[static] [bit] NOT NULL,
	[roleId] [int] NULL,
	[key1] [varchar](20) NULL,
	[key2] [int] NULL,
 CONSTRAINT [PK_TEACHER] PRIMARY KEY CLUSTERED 
(
	[teaNum] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
/****** Object:  Table [dbo].[classInfo]    Script Date: 09/21/2021 22:18:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[classInfo](
	[clsId] [int] IDENTITY(1,1) NOT NULL,
	[clsName] [char](10) NULL,
	[grade] [datetime] NULL,
	[stuCount] [int] NULL,
	[teaNum] [char](8) NULL,
	[key1] [varchar](20) NULL,
	[key2] [int] NULL,
 CONSTRAINT [PK_CLASSINFO] PRIMARY KEY CLUSTERED 
(
	[clsId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[classInfo]  WITH CHECK ADD  CONSTRAINT [FK_CLASSINF_REFERENCE_TEACHER] FOREIGN KEY([teaNum])
REFERENCES [dbo].[teacher] ([teaNum])
GO
ALTER TABLE [dbo].[classInfo] CHECK CONSTRAINT [FK_CLASSINF_REFERENCE_TEACHER]

/****** Object:  Table [dbo].[student]    Script Date: 09/21/2021 22:19:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[student](
	[stuNum] [char](12) NOT NULL,
	[clsId] [int] NULL,
	[stuName] [varchar](20) NOT NULL,
	[stuBtd] [datetime] NOT NULL,
	[pwd] [varchar](20) NOT NULL,
	[static] [int] NOT NULL,
	[sex] [bit] NULL,
	[roleId] [int] NULL,
	[key1] [varchar](20) NULL,
	[key2] [int] NULL,
 CONSTRAINT [PK_STUDENT] PRIMARY KEY CLUSTERED 
(
	[stuNum] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[student]  WITH CHECK ADD  CONSTRAINT [FK_STUDENT_REFERENCE_CLASSINF] FOREIGN KEY([clsId])
REFERENCES [dbo].[classInfo] ([clsId])
GO
ALTER TABLE [dbo].[student] CHECK CONSTRAINT [FK_STUDENT_REFERENCE_CLASSINF]

/****** Object:  Table [dbo].[restPwd]    Script Date: 09/21/2021 22:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[restPwd](
	[stuNum] [char](12) NULL,
	[question1] [varchar](20) NULL,
	[answer1] [varchar](20) NULL,
	[question2] [varchar](20) NULL,
	[answer2] [varchar](20) NULL,
	[question3] [varchar](20) NULL,
	[answer3] [varchar](20) NULL,
	[count] [int] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[restPwd]  WITH CHECK ADD  CONSTRAINT [FK_RESTPWD_REFERENCE_STUDENT] FOREIGN KEY([stuNum])
REFERENCES [dbo].[student] ([stuNum])
GO
ALTER TABLE [dbo].[restPwd] CHECK CONSTRAINT [FK_RESTPWD_REFERENCE_STUDENT]


/****** Object:  Table [dbo].[courseArrangement]    Script Date: 09/21/2021 22:09:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[courseArrangement](
	[caId] [int] IDENTITY(1,1) NOT NULL,
	[lesId] [int] NULL,
	[teaNum] [char](8) NULL,
	[clsId] [int] NULL,
	[year] [char](4) NULL,
	[semester] [int] NULL,
 CONSTRAINT [PK_COURSEARRANGEMENT] PRIMARY KEY CLUSTERED 
(
	[caId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[courseArrangement]  WITH CHECK ADD  CONSTRAINT [FK_COURSEAR_REFERENCE_CLASSINF] FOREIGN KEY([clsId])
REFERENCES [dbo].[classInfo] ([clsId])
GO
ALTER TABLE [dbo].[courseArrangement] CHECK CONSTRAINT [FK_COURSEAR_REFERENCE_CLASSINF]
GO
ALTER TABLE [dbo].[courseArrangement]  WITH CHECK ADD  CONSTRAINT [FK_COURSEAR_REFERENCE_LESSON] FOREIGN KEY([lesId])
REFERENCES [dbo].[lesson] ([lesId])
GO
ALTER TABLE [dbo].[courseArrangement] CHECK CONSTRAINT [FK_COURSEAR_REFERENCE_LESSON]
GO
ALTER TABLE [dbo].[courseArrangement]  WITH CHECK ADD  CONSTRAINT [FK_COURSEAR_REFERENCE_TEACHER] FOREIGN KEY([teaNum])
REFERENCES [dbo].[teacher] ([teaNum])
GO
ALTER TABLE [dbo].[courseArrangement] CHECK CONSTRAINT [FK_COURSEAR_REFERENCE_TEACHER]

/****** Object:  Table [dbo].[examSchedule]    Script Date: 09/21/2021 22:09:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[examSchedule](
	[esId] [int] IDENTITY(1,1) NOT NULL,
	[caId] [int] NULL,
	[examDate] [datetime] NOT NULL,
	[static] [int] NOT NULL,
	[context] [varchar](20) NULL,
 CONSTRAINT [PK_EXAMSCHEDULE] PRIMARY KEY CLUSTERED 
(
	[esId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[examSchedule]  WITH CHECK ADD  CONSTRAINT [FK_EXAMSCHE_REFERENCE_COURSEAR] FOREIGN KEY([caId])
REFERENCES [dbo].[courseArrangement] ([caId])
GO
ALTER TABLE [dbo].[examSchedule] CHECK CONSTRAINT [FK_EXAMSCHE_REFERENCE_COURSEAR]


/****** Object:  Table [dbo].[Score]    Script Date: 09/21/2021 22:09:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Score](
	[scoreId] [int] IDENTITY(1,1) NOT NULL,
	[esId] [int] NULL,
	[stuNum] [char](12) NULL,
	[score] [int] NOT NULL,
 CONSTRAINT [PK_SCORE] PRIMARY KEY CLUSTERED 
(
	[scoreId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Score]  WITH CHECK ADD  CONSTRAINT [FK_SCORE_REFERENCE_EXAMSCHE] FOREIGN KEY([esId])
REFERENCES [dbo].[examSchedule] ([esId])
GO
ALTER TABLE [dbo].[Score] CHECK CONSTRAINT [FK_SCORE_REFERENCE_EXAMSCHE]
GO
ALTER TABLE [dbo].[Score]  WITH CHECK ADD  CONSTRAINT [FK_SCORE_REFERENCE_STUDENT] FOREIGN KEY([stuNum])
REFERENCES [dbo].[student] ([stuNum])
GO
ALTER TABLE [dbo].[Score] CHECK CONSTRAINT [FK_SCORE_REFERENCE_STUDENT]