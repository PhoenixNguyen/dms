USE [DMS]
GO
/****** Object:  Table [dbo].[tb_calendar]    Script Date: 11/9/2014 2:49:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_calendar](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[staff_id] [varchar](100) NOT NULL,
	[calendar_date] [date] NOT NULL,
	[province] [nvarchar](100) NOT NULL,
	[content] [ntext] NOT NULL,
	[report] [ntext] NULL,
	[contributor] [nvarchar](100) NULL,
	[mission] [nvarchar](200) NULL,
	[support] [nvarchar](200) NULL,
	[status] [int] NOT NULL,
	[coordinate_x] [float] NOT NULL,
	[coordinate_y] [float] NOT NULL,
	[note] [ntext] NULL,
	[created_time] [datetime] NULL,
	[updated_time] [datetime] NULL,
 CONSTRAINT [PK_tb_calendar_1] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_chitietdonbanhang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_chitietdonbanhang](
	[chitietdonbanhang_stt] [int] IDENTITY(1,1) NOT NULL,
	[chitietdonbanhang_ma_hoa_don] [varchar](100) NULL,
	[chitietdonbanhang_dong] [int] NOT NULL,
	[chitietdonbanhang_ma_hang] [varchar](100) NULL,
	[chitietdonbanhang_ma_vach] [varchar](100) NULL,
	[chitietdonbanhang_ten_san_pham] [nvarchar](200) NULL,
	[chitietdonbanhang_don_gia_sau_thue] [float] NOT NULL,
	[chitietdonbanhang_don_gia_truoc_thue] [float] NOT NULL,
	[chitietdonbanhang_thue] [float] NOT NULL,
	[chitietdonbanhang_giam_gia] [int] NOT NULL,
	[chitietdonbanhang_thanh_tien] [float] NOT NULL,
	[chitietdonbanhang_ma_kho] [varchar](100) NULL,
	[chitietdonbanhang_so_luong] [int] NOT NULL,
	[chitietdonbanhang_don_vi_tinh] [nvarchar](100) NULL,
	[chitietdonbanhang_ty_gia] [float] NOT NULL,
	[chitietdonbanhang_ghi_chu] [nvarchar](200) NULL,
	[chitietdonbanhang_hang_khuyen_mai] [int] NOT NULL,
 CONSTRAINT [PK_tb_chitietdonbanhang_1] PRIMARY KEY CLUSTERED 
(
	[chitietdonbanhang_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_chitietdondathang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_chitietdondathang](
	[chitietdondathang_stt] [int] IDENTITY(1,1) NOT NULL,
	[chitietdondathang_ma_hoa_don] [varchar](100) NULL,
	[chitietdondathang_dong] [int] NULL,
	[chitietdondathang_ma_hang] [varchar](100) NULL,
	[chitietdondathang_ma_vach] [varchar](100) NULL,
	[chitietdondathang_ten_san_pham] [nvarchar](200) NULL,
	[chitietdondathang_don_gia_sau_thue] [float] NOT NULL,
	[chitietdondathang_don_gia_truoc_thue] [float] NOT NULL,
	[chitietdondathang_thue] [float] NOT NULL,
	[chitietdondathang_giam_gia] [int] NOT NULL,
	[chitietdondathang_thanh_tien] [float] NOT NULL,
	[chitietdondathang_ma_kho] [varchar](100) NULL,
	[chitietdondathang_so_luong] [int] NOT NULL,
	[chitietdondathang_don_vi_tinh] [nvarchar](100) NULL,
	[chitietdondathang_ty_gia] [float] NOT NULL,
	[chitietdondathang_ghi_chu] [nvarchar](200) NULL,
	[chitietdondathang_hang_khuyen_mai] [int] NOT NULL,
 CONSTRAINT [PK_tb_chitietdondathang_1] PRIMARY KEY CLUSTERED 
(
	[chitietdondathang_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_chitietdontrahang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_chitietdontrahang](
	[chitietdontrahang_stt] [int] IDENTITY(1,1) NOT NULL,
	[chitietdontrahang_ma_hoa_don] [varchar](100) NOT NULL,
	[chitietdontrahang_dong] [int] NOT NULL,
	[chitietdontrahang_ma_hang] [varchar](100) NULL,
	[chitietdontrahang_ma_vach] [varchar](100) NULL,
	[chitietdontrahang_ten_san_pham] [nvarchar](200) NULL,
	[chitietdontrahang_don_gia_sau_thue] [float] NOT NULL,
	[chitietdontrahang_don_gia_truoc_thue] [float] NOT NULL,
	[chitietdontrahang_thue] [float] NOT NULL,
	[chitietdontrahang_giam_gia] [int] NOT NULL,
	[chitietdontrahang_thanh_tien] [float] NOT NULL,
	[chitietdontrahang_ma_kho] [varchar](100) NULL,
	[chitietdontrahang_so_luong] [int] NOT NULL,
	[chitietdontrahang_don_vi_tinh] [nvarchar](100) NULL,
	[chitietdontrahang_ty_gia] [float] NOT NULL,
	[chitietdontrahang_ghi_chu] [nvarchar](200) NULL,
	[chitietdontrahang_hang_khuyen_mai] [int] NOT NULL,
 CONSTRAINT [PK_tb_chitietdontrahang_1] PRIMARY KEY CLUSTERED 
(
	[chitietdontrahang_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_forleave]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_forleave](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[staff_id] [varchar](100) NOT NULL,
	[time_at] [date] NOT NULL,
	[created_time] [datetime] NOT NULL,
	[content] [nvarchar](200) NULL,
	[note] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_forleave] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_hoadonbanhang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_hoadonbanhang](
	[hoadonbanhang_stt] [int] IDENTITY(1,1) NOT NULL,
	[hoadonbanhang_ma_hoa_don] [varchar](100) NOT NULL,
	[hoadonbanhang_ngay_ban_hang] [datetime] NULL,
	[hoadonbanhang_ngay_giao_hang_du_kien] [datetime] NULL,
	[hoadonbanhang_ma_khach_hang] [varchar](100) NOT NULL,
	[hoadonbanhang_ten_khach_hang] [nvarchar](100) NULL,
	[hoadonbanhang_dia_chi] [nvarchar](100) NULL,
	[hoadonbanhang_so_dien_thoai] [nvarchar](50) NULL,
	[hoadonbanhang_dia_chi_giao_hang] [nvarchar](100) NULL,
	[hoadonbanhang_hinh_thuc_van_chuyen] [nvarchar](100) NULL,
	[hoadonbanhang_thue] [float] NOT NULL,
	[hoadonbanhang_tien_truoc_thue] [float] NOT NULL,
	[hoadonbanhang_tien_sau_thue] [float] NOT NULL,
	[hoadonbanhang_giam_gia] [float] NOT NULL,
	[hoadonbanhang_trang_thai_don_hang] [int] NOT NULL,
	[hoadonbanhang_ngay_tao_hoa_don] [datetime] NULL,
	[hoadonbanhang_ngay_sua_hoa_don] [datetime] NULL,
	[hoadonbanhang_nguoi_tao] [varchar](100) NULL,
	[hoadonbanhang_nguoi_sua] [varchar](100) NULL,
	[hoadonbanhang_ghi_chu] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_hoadonbanhang] PRIMARY KEY CLUSTERED 
(
	[hoadonbanhang_ma_hoa_don] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_hoadondathang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_hoadondathang](
	[hoadondathang_stt] [int] IDENTITY(1,1) NOT NULL,
	[hoadondathang_ma_hoa_don] [varchar](100) NOT NULL,
	[hoadondathang_ngay_dat_hang] [datetime] NULL,
	[hoadondathang_ngay_giao_hang_du_kien] [datetime] NULL,
	[hoadondathang_ma_khach_hang] [varchar](100) NULL,
	[hoadondathang_ten_khach_hang] [nvarchar](100) NULL,
	[hoadondathang_dia_chi] [nvarchar](100) NULL,
	[hoadondathang_so_dien_thoai] [nvarchar](50) NULL,
	[hoadondathang_dia_chi_giao_hang] [nvarchar](100) NULL,
	[hoadondathang_hinh_thuc_van_chuyen] [nvarchar](100) NULL,
	[hoadondathang_thue] [float] NOT NULL,
	[hoadondathang_tien_truoc_thue] [float] NOT NULL,
	[hoadondathang_tien_sau_thue] [float] NOT NULL,
	[hoadondathang_giam_gia] [float] NOT NULL,
	[hoadondathang_trang_thai_don_hang] [int] NOT NULL,
	[hoadondathang_ngay_tao_hoa_don] [datetime] NULL,
	[hoadondathang_ngay_sua_hoa_don] [datetime] NULL,
	[hoadondathang_nguoi_tao] [varchar](100) NULL,
	[hoadondathang_nguoi_sua] [varchar](100) NULL,
	[hoadondathang_ghi_chu] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_hoadondathang] PRIMARY KEY CLUSTERED 
(
	[hoadondathang_ma_hoa_don] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_hoadontrahang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_hoadontrahang](
	[hoadontrahang_stt] [int] IDENTITY(1,1) NOT NULL,
	[hoadontrahang_ma_hoa_don] [varchar](100) NOT NULL,
	[hoadontrahang_ngay_tra_hang] [datetime] NULL,
	[hoadontrahang_ngay_giao_hang_du_kien] [datetime] NULL,
	[hoadontrahang_ma_khach_hang] [varchar](100) NOT NULL,
	[hoadontrahang_ten_khach_hang] [nvarchar](100) NULL,
	[hoadontrahang_dia_chi] [nvarchar](100) NULL,
	[hoadontrahang_so_dien_thoai] [nvarchar](50) NULL,
	[hoadontrahang_dia_chi_giao_hang] [nvarchar](100) NULL,
	[hoadontrahang_hinh_thuc_van_chuyen] [nvarchar](100) NULL,
	[hoadontrahang_thue] [float] NOT NULL,
	[hoadontrahang_tien_truoc_thue] [float] NOT NULL,
	[hoadontrahang_tien_sau_thue] [float] NOT NULL,
	[hoadontrahang_giam_gia] [float] NOT NULL,
	[hoadontrahang_trang_thai_don_hang] [int] NOT NULL,
	[hoadontrahang_ngay_tao_hoa_don] [datetime] NULL,
	[hoadontrahang_ngay_sua_hoa_don] [datetime] NULL,
	[hoadontrahang_nguoi_tao] [varchar](100) NULL,
	[hoadontrahang_nguoi_sua] [varchar](100) NULL,
	[hoadontrahang_ghi_chu] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_hoadontrahang] PRIMARY KEY CLUSTERED 
(
	[hoadontrahang_ma_hoa_don] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_khachhang]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_khachhang](
	[khachhang_stt] [int] IDENTITY(1,1) NOT NULL,
	[khachhang_tinh_thanh] [nvarchar](100) NULL,
	[khachhang_tuyen_ban_hang_thu] [nvarchar](100) NULL,
	[khachhang_ma_nv] [varchar](100) NULL,
	[khachhang_x] [nvarchar](100) NULL,
	[khachhang_ma_dt] [varchar](100) NOT NULL,
	[khachhang_doi_tuong] [nvarchar](200) NULL,
	[khachhang_diachi] [nvarchar](100) NULL,
	[khachhang_dien_thoai] [nvarchar](100) NULL,
	[khachhang_fax] [nvarchar](100) NULL,
	[khachhang_ghi_chu] [nvarchar](200) NULL,
	[khachhang_toa_do_x] [float] NULL,
	[khachhang_toa_do_y] [float] NULL,
 CONSTRAINT [PK_tb_khachhang] PRIMARY KEY CLUSTERED 
(
	[khachhang_ma_dt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_khachhang_hinhanh]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_khachhang_hinhanh](
	[hinhanh_stt] [int] IDENTITY(1,1) NOT NULL,
	[hinhanh_id] [varchar](100) NOT NULL,
	[hinhanh_name] [nvarchar](200) NULL,
	[hinhanh_ma_khach_hang] [varchar](100) NULL,
	[hinhanh_ma_nhan_vien] [varchar](100) NULL,
	[hinhanh_ghi_chu] [nvarchar](200) NULL,
	[hinhanh_time] [datetime] NULL,
	[hinhanh_trang_thai] [bit] NOT NULL,
 CONSTRAINT [PK_tb_khachhang_hinhanh] PRIMARY KEY CLUSTERED 
(
	[hinhanh_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_kho]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_kho](
	[kho_stt] [int] IDENTITY(1,1) NOT NULL,
	[kho_ma_kho] [varchar](100) NOT NULL,
	[kho_ma_san_pham] [varchar](100) NOT NULL,
	[kho_ten_san_pham] [nvarchar](100) NULL,
	[kho_gia] [float] NULL,
	[kho_ton_dau] [float] NULL,
	[kho_nhap] [float] NULL,
	[kho_ton_cuoi] [float] NULL,
	[kho_ban_ra] [float] NULL,
	[kho_order] [float] NULL,
	[kho_duyet] [float] NULL,
	[kho_ton_tong_kho] [float] NULL,
	[kho_ghi_chu] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_kho_1] PRIMARY KEY CLUSTERED 
(
	[kho_ma_kho] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_kiemkho]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_kiemkho](
	[kiemkho_stt] [int] IDENTITY(1,1) NOT NULL,
	[kiemkho_ma_hoa_don] [varchar](100) NOT NULL,
	[kiemkho_ngay_dat_hang] [datetime] NULL,
	[kiemkho_ngay_giao_hang_du_kien] [datetime] NULL,
	[kiemkho_ma_khach_hang] [varchar](100) NOT NULL,
	[kiemkho_ten_khach_hang] [nvarchar](100) NULL,
	[kiemkho_dia_chi] [nvarchar](100) NULL,
	[kiemkho_so_dien_thoai] [nvarchar](50) NULL,
	[kiemkho_dia_chi_giao_hang] [nvarchar](100) NULL,
	[kiemkho_hinh_thuc_van_chuyen] [nvarchar](100) NULL,
	[kiemkho_thue] [float] NULL,
	[kiemkho_tien_truoc_thue] [float] NULL,
	[kiemkho_tien_sau_thue] [float] NULL,
	[kiemkho_giam_gia] [float] NULL,
	[kiemkho_trang_thai_don_hang] [int] NULL,
	[kiemkho_ngay_tao_hoa_don] [datetime] NULL,
	[kiemkho_ngay_sua_hoa_don] [datetime] NULL,
	[kiemkho_nguoi_tao] [varchar](100) NULL,
	[kiemkho_nguoi_sua] [varchar](100) NULL,
	[kiemkho_ghi_chu] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_kiemkho] PRIMARY KEY CLUSTERED 
(
	[kiemkho_ma_hoa_don] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_kiemkhochitiet]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_kiemkhochitiet](
	[kiemkhochitiet_stt] [int] IDENTITY(1,1) NOT NULL,
	[kiemkhochitiet_ma_hoa_don] [varchar](100) NULL,
	[kiemkhochitiet_dong] [int] NULL,
	[kiemkhochitiet_ma_hang] [varchar](100) NULL,
	[kiemkhochitiet_ma_vach] [varchar](100) NULL,
	[kiemkhochitiet_ten_san_pham] [nvarchar](200) NULL,
	[kiemkhochitiet_don_gia_sau_thue] [float] NULL,
	[kiemkhochitiet_don_gia_truoc_thue] [float] NULL,
	[kiemkhochitiet_thue] [float] NULL,
	[kiemkhochitiet_giam_gia] [int] NULL,
	[kiemkhochitiet_thanh_tien] [float] NULL,
	[kiemkhochitiet_ma_kho] [varchar](100) NULL,
	[kiemkhochitiet_so_luong] [int] NULL,
	[kiemkhochitiet_don_vi_tinh] [nvarchar](100) NULL,
	[kiemkhochitiet_ty_gia] [float] NULL,
	[kiemkhochitiet_ghi_chu] [nvarchar](200) NULL,
	[kiemkhochitiet_hang_khuyen_mai] [int] NULL,
 CONSTRAINT [PK_tb_kiemkhochitiet_1] PRIMARY KEY CLUSTERED 
(
	[kiemkhochitiet_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_nhacungcap]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_nhacungcap](
	[nhacungcap_stt] [int] IDENTITY(1,1) NOT NULL,
	[nhacungcap_id] [varchar](100) NOT NULL,
	[nhacungcap_ten] [nvarchar](200) NULL,
	[nhacungcap_dia_chi] [nvarchar](100) NULL,
	[nhacungcap_dien_thoai] [nvarchar](100) NULL,
	[nhacungcap_fax] [nvarchar](100) NULL,
	[nhacungcap_ghi_chu] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_nhacungcap] PRIMARY KEY CLUSTERED 
(
	[nhacungcap_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_nhanvien]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_nhanvien](
	[nhanvien_stt] [int] IDENTITY(1,1) NOT NULL,
	[nhanvien_ma_nhan_vien] [varchar](100) NOT NULL,
	[nhanvien_mat_khau] [varchar](100) NULL,
	[nhanvien_ho_ten] [nvarchar](100) NULL,
	[nhanvien_dia_chi] [nvarchar](100) NULL,
	[nhanvien_chuc_vu] [nvarchar](100) NULL,
	[nhanvien_so_dien_thoai] [nvarchar](100) NULL,
	[nhanvien_ngay_gia_nhap] [date] NULL,
	[nhanvien_nguoi_quan_ly] [varchar](100) NULL,
	[nhanvien_trang_thai] [bit] NOT NULL,
	[nhanvien_permission] [int] NOT NULL,
 CONSTRAINT [PK_tb_nhanvien] PRIMARY KEY CLUSTERED 
(
	[nhanvien_ma_nhan_vien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UK_krofhg13skfo07cdnlrf1bf1t] UNIQUE NONCLUSTERED 
(
	[nhanvien_ma_nhan_vien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_quanlyduongdi]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_quanlyduongdi](
	[quanlyduongdi_stt] [int] IDENTITY(1,1) NOT NULL,
	[quanlyduongdi_ma_nhan_vien] [varchar](100) NOT NULL,
	[quanlyduongdi_ten_nhan_vien] [varchar](100) NOT NULL,
	[quanlyduongdi_thoi_gian] [datetime] NOT NULL,
	[quanlyduongdi_vi_do] [float] NOT NULL,
	[quanlyduongdi_kinh_do] [float] NOT NULL,
	[quanlyduongdi_ghi_chu] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_quanlyduongdi] PRIMARY KEY CLUSTERED 
(
	[quanlyduongdi_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_quanlygiamdoc]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_quanlygiamdoc](
	[quanlygiamdoc_id] [int] NOT NULL,
	[quanlygiamdoc_tong_giam_doc] [varchar](100) NOT NULL,
	[quanlygiamdoc_giamdoc] [varchar](100) NOT NULL,
	[quanlygiamdoc_ghi_chu] [nvarchar](100) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_sanpham]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_sanpham](
	[sanpham_stt] [int] IDENTITY(1,1) NOT NULL,
	[sanpham_ma_san_pham] [varchar](100) NOT NULL,
	[sanpham_ma_vach] [nvarchar](100) NULL,
	[sanpham_ten_hang_hoa] [nvarchar](200) NULL,
	[sanpham_thuong_hieu] [nvarchar](100) NULL,
	[sanpham_xuat_xu] [nvarchar](100) NULL,
	[sanpham_quy_cach_packing] [nvarchar](100) NULL,
	[sanpham_dinh_luong] [nvarchar](100) NULL,
	[sanpham_thue] [float] NOT NULL,
	[sanpham_gia_nhap] [float] NOT NULL,
	[sanpham_gia_ban] [float] NOT NULL,
	[sanpham_nha_cung_cap] [varchar](100) NULL,
	[sanpham_mo_ta] [nvarchar](200) NULL,
	[sanpham_anh_san_pham] [nvarchar](100) NULL,
 CONSTRAINT [PK_tb_sanpham] PRIMARY KEY CLUSTERED 
(
	[sanpham_ma_san_pham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_schedule]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_schedule](
	[schedule_stt] [int] IDENTITY(1,1) NOT NULL,
	[schedule_ma_nv] [varchar](100) NOT NULL,
	[schedule_ma_khach_hang] [varchar](100) NOT NULL,
	[schedule_date] [datetime] NOT NULL,
	[schedule_trang_thai] [bit] NOT NULL,
	[schedule_ten_khach_hang] [nvarchar](200) NULL,
	[schedule_ten_nhan_vien] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_schedule] PRIMARY KEY CLUSTERED 
(
	[schedule_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_setlunch]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_setlunch](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[staff_id] [varchar](100) NOT NULL,
	[time_at] [date] NOT NULL,
	[created_time] [datetime] NOT NULL,
	[content] [nvarchar](200) NULL,
	[note] [nvarchar](200) NULL,
 CONSTRAINT [PK_tb_setlunch] PRIMARY KEY CLUSTERED 
(
	[stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_staffhistory]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_staffhistory](
	[history_stt] [int] IDENTITY(1,1) NOT NULL,
	[history_staff] [varchar](100) NOT NULL,
	[history_customer] [varchar](100) NOT NULL,
	[history_customer_name] [varchar](100) NULL,
	[history_time] [datetime] NULL,
	[history_note] [varchar](200) NULL,
 CONSTRAINT [PK_tb_staffhistory] PRIMARY KEY CLUSTERED 
(
	[history_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_thongbao]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_thongbao](
	[thongbao_stt] [int] IDENTITY(1,1) NOT NULL,
	[thongbao_nguoi_tao] [varchar](100) NOT NULL,
	[thongbao_ten] [varchar](200) NULL,
	[thongbao_noi_dung] [ntext] NULL,
	[thongbao_ngay_tao] [datetime] NULL,
	[thongbao_ghi_chu] [varchar](200) NULL,
	[thongbao_trang_thai] [bit] NOT NULL,
 CONSTRAINT [PK_tb_thongbao] PRIMARY KEY CLUSTERED 
(
	[thongbao_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_timekeeper]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_timekeeper](
	[stt] [int] IDENTITY(1,1) NOT NULL,
	[staff_id] [varchar](100) NOT NULL,
	[time_at] [datetime] NOT NULL,
	[province] [nvarchar](200) NULL,
	[time_between] [float] NULL,
	[note] [nvarchar](200) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_user]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_user](
	[user_stt] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](100) NOT NULL,
	[user_chuc_danh] [nvarchar](100) NULL,
	[user_ho_ten] [nvarchar](100) NULL,
	[user_email] [nvarchar](100) NULL,
	[user_so_dien_thoai] [nvarchar](100) NULL,
	[user_dia_chi] [nvarchar](100) NULL,
	[user_ghi_chu] [nvarchar](100) NULL,
	[user_ngay_tham_gia] [date] NULL,
	[user_permission] [int] NOT NULL,
	[user_status] [bit] NOT NULL,
	[user_pw] [varchar](100) NOT NULL,
 CONSTRAINT [PK_tb_user] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tb_userhistory]    Script Date: 11/9/2014 2:49:24 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tb_userhistory](
	[history_stt] [int] IDENTITY(1,1) NOT NULL,
	[history_user] [varchar](100) NOT NULL,
	[history_ip] [varchar](100) NULL,
	[history_login] [datetime] NULL,
	[history_logout] [datetime] NULL,
	[history_note] [varchar](200) NULL,
 CONSTRAINT [PK_history] PRIMARY KEY CLUSTERED 
(
	[history_stt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[tb_calendar] ADD  CONSTRAINT [DF_tb_calendar_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[tb_calendar] ADD  CONSTRAINT [DF_tb_calendar_coordinate_x]  DEFAULT ((0)) FOR [coordinate_x]
GO
ALTER TABLE [dbo].[tb_calendar] ADD  CONSTRAINT [DF_tb_calendar_coordinate_y]  DEFAULT ((0)) FOR [coordinate_y]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_dong]  DEFAULT ((0)) FOR [chitietdonbanhang_dong]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_don_gia_sau_thue]  DEFAULT ((0)) FOR [chitietdonbanhang_don_gia_sau_thue]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_don_gia_truoc_thue]  DEFAULT ((0)) FOR [chitietdonbanhang_don_gia_truoc_thue]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_thue]  DEFAULT ((0)) FOR [chitietdonbanhang_thue]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_giam_gia]  DEFAULT ((0)) FOR [chitietdonbanhang_giam_gia]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_thanh_tien]  DEFAULT ((0)) FOR [chitietdonbanhang_thanh_tien]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_so_luong]  DEFAULT ((0)) FOR [chitietdonbanhang_so_luong]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_ty_gia]  DEFAULT ((0)) FOR [chitietdonbanhang_ty_gia]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] ADD  CONSTRAINT [DF_tb_chitietdonbanhang_chitietdonbanhang_hang_khuyen_mai]  DEFAULT ((0)) FOR [chitietdonbanhang_hang_khuyen_mai]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_don_gia_sau_thue]  DEFAULT ((0)) FOR [chitietdondathang_don_gia_sau_thue]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_don_gia_truoc_thue]  DEFAULT ((0)) FOR [chitietdondathang_don_gia_truoc_thue]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_thue]  DEFAULT ((0)) FOR [chitietdondathang_thue]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_giam_gia]  DEFAULT ((0)) FOR [chitietdondathang_giam_gia]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_thanh_tien]  DEFAULT ((0)) FOR [chitietdondathang_thanh_tien]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_so_luong]  DEFAULT ((0)) FOR [chitietdondathang_so_luong]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_ty_gia]  DEFAULT ((0)) FOR [chitietdondathang_ty_gia]
GO
ALTER TABLE [dbo].[tb_chitietdondathang] ADD  CONSTRAINT [DF_tb_chitietdondathang_chitietdondathang_hang_khuyen_mai]  DEFAULT ((0)) FOR [chitietdondathang_hang_khuyen_mai]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_dong]  DEFAULT ((0)) FOR [chitietdontrahang_dong]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_don_gia_sau_thue]  DEFAULT ((0)) FOR [chitietdontrahang_don_gia_sau_thue]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_don_gia_truoc_thue]  DEFAULT ((0)) FOR [chitietdontrahang_don_gia_truoc_thue]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_thue]  DEFAULT ((0)) FOR [chitietdontrahang_thue]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_giam_gia]  DEFAULT ((0)) FOR [chitietdontrahang_giam_gia]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_thanh_tien]  DEFAULT ((0)) FOR [chitietdontrahang_thanh_tien]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_so_luong]  DEFAULT ((0)) FOR [chitietdontrahang_so_luong]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_ty_gia]  DEFAULT ((0)) FOR [chitietdontrahang_ty_gia]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] ADD  CONSTRAINT [DF_tb_chitietdontrahang_chitietdontrahang_hang_khuyen_mai]  DEFAULT ((0)) FOR [chitietdontrahang_hang_khuyen_mai]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] ADD  CONSTRAINT [DF_tb_hoadonbanhang_hoadonbanhang_thue]  DEFAULT ((0)) FOR [hoadonbanhang_thue]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] ADD  CONSTRAINT [DF_tb_hoadonbanhang_hoadonbanhang_tien_truoc_thue]  DEFAULT ((0)) FOR [hoadonbanhang_tien_truoc_thue]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] ADD  CONSTRAINT [DF_tb_hoadonbanhang_hoadonbanhang_tien_sau_thue]  DEFAULT ((0)) FOR [hoadonbanhang_tien_sau_thue]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] ADD  CONSTRAINT [DF_tb_hoadonbanhang_hoadonbanhang_giam_gia]  DEFAULT ((0)) FOR [hoadonbanhang_giam_gia]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] ADD  CONSTRAINT [DF_tb_hoadonbanhang_hoadonbanhang_trang_thai_don_hang]  DEFAULT ((0)) FOR [hoadonbanhang_trang_thai_don_hang]
GO
ALTER TABLE [dbo].[tb_hoadondathang] ADD  CONSTRAINT [DF_tb_hoadondathang_hoadondathang_thue]  DEFAULT ((0)) FOR [hoadondathang_thue]
GO
ALTER TABLE [dbo].[tb_hoadondathang] ADD  CONSTRAINT [DF_tb_hoadondathang_hoadondathang_tien_truoc_thue]  DEFAULT ((0)) FOR [hoadondathang_tien_truoc_thue]
GO
ALTER TABLE [dbo].[tb_hoadondathang] ADD  CONSTRAINT [DF_tb_hoadondathang_hoadondathang_tien_sau_thue]  DEFAULT ((0)) FOR [hoadondathang_tien_sau_thue]
GO
ALTER TABLE [dbo].[tb_hoadondathang] ADD  CONSTRAINT [DF_tb_hoadondathang_hoadondathang_giam_gia]  DEFAULT ((0)) FOR [hoadondathang_giam_gia]
GO
ALTER TABLE [dbo].[tb_hoadondathang] ADD  CONSTRAINT [DF_tb_hoadondathang_hoadondathang_trang_thai_don_hang]  DEFAULT ((0)) FOR [hoadondathang_trang_thai_don_hang]
GO
ALTER TABLE [dbo].[tb_hoadontrahang] ADD  CONSTRAINT [DF_tb_hoadontrahang_hoadontrahang_thue]  DEFAULT ((0)) FOR [hoadontrahang_thue]
GO
ALTER TABLE [dbo].[tb_hoadontrahang] ADD  CONSTRAINT [DF_tb_hoadontrahang_hoadontrahang_tien_truoc_thue]  DEFAULT ((0)) FOR [hoadontrahang_tien_truoc_thue]
GO
ALTER TABLE [dbo].[tb_hoadontrahang] ADD  CONSTRAINT [DF_tb_hoadontrahang_hoadontrahang_tien_sau_thue]  DEFAULT ((0)) FOR [hoadontrahang_tien_sau_thue]
GO
ALTER TABLE [dbo].[tb_hoadontrahang] ADD  CONSTRAINT [DF_tb_hoadontrahang_hoadontrahang_giam_gia]  DEFAULT ((0)) FOR [hoadontrahang_giam_gia]
GO
ALTER TABLE [dbo].[tb_hoadontrahang] ADD  CONSTRAINT [DF_tb_hoadontrahang_hoadontrahang_trang_thai_don_hang]  DEFAULT ((0)) FOR [hoadontrahang_trang_thai_don_hang]
GO
ALTER TABLE [dbo].[tb_khachhang] ADD  CONSTRAINT [DF_tb_khachhang_khachhang_toa_do_x]  DEFAULT ((0)) FOR [khachhang_toa_do_x]
GO
ALTER TABLE [dbo].[tb_khachhang] ADD  CONSTRAINT [DF_tb_khachhang_khachhang_toa_do_y]  DEFAULT ((0)) FOR [khachhang_toa_do_y]
GO
ALTER TABLE [dbo].[tb_khachhang_hinhanh] ADD  CONSTRAINT [DF_tb_khachhang_hinhanh_hinhanh_trang_thai]  DEFAULT ((0)) FOR [hinhanh_trang_thai]
GO
ALTER TABLE [dbo].[tb_kiemkho] ADD  CONSTRAINT [DF_tb_kiemkho_kiemkho_thue]  DEFAULT ((0)) FOR [kiemkho_thue]
GO
ALTER TABLE [dbo].[tb_kiemkho] ADD  CONSTRAINT [DF_tb_kiemkho_kiemkho_tien_truoc_thue]  DEFAULT ((0)) FOR [kiemkho_tien_truoc_thue]
GO
ALTER TABLE [dbo].[tb_kiemkho] ADD  CONSTRAINT [DF_tb_kiemkho_kiemkho_tien_sau_thue]  DEFAULT ((0)) FOR [kiemkho_tien_sau_thue]
GO
ALTER TABLE [dbo].[tb_kiemkho] ADD  CONSTRAINT [DF_tb_kiemkho_kiemkho_giam_gia]  DEFAULT ((0)) FOR [kiemkho_giam_gia]
GO
ALTER TABLE [dbo].[tb_kiemkho] ADD  CONSTRAINT [DF_tb_kiemkho_kiemkho_trang_thai_don_hang]  DEFAULT (NULL) FOR [kiemkho_trang_thai_don_hang]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_don_gia_sau_thue]  DEFAULT ((0)) FOR [kiemkhochitiet_don_gia_sau_thue]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_don_gia_truoc_thue]  DEFAULT ((0)) FOR [kiemkhochitiet_don_gia_truoc_thue]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_thue]  DEFAULT ((0)) FOR [kiemkhochitiet_thue]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_giam_gia]  DEFAULT ((0)) FOR [kiemkhochitiet_giam_gia]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_thanh_tien]  DEFAULT ((0)) FOR [kiemkhochitiet_thanh_tien]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_so_luong]  DEFAULT ((0)) FOR [kiemkhochitiet_so_luong]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_ty_gia]  DEFAULT ((0)) FOR [kiemkhochitiet_ty_gia]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] ADD  CONSTRAINT [DF_tb_kiemkhochitiet_kiemkhochitiet_hang_khuyen_mai]  DEFAULT ((0)) FOR [kiemkhochitiet_hang_khuyen_mai]
GO
ALTER TABLE [dbo].[tb_nhanvien] ADD  CONSTRAINT [DF_tb_nhanvien_nhanvien_mat_khau]  DEFAULT ((123456)) FOR [nhanvien_mat_khau]
GO
ALTER TABLE [dbo].[tb_nhanvien] ADD  CONSTRAINT [DF_tb_nhanvien_nhanvien_nguoi_quan_ly]  DEFAULT ('kachiusa') FOR [nhanvien_nguoi_quan_ly]
GO
ALTER TABLE [dbo].[tb_nhanvien] ADD  CONSTRAINT [DF_tb_nhanvien_nhanvien_trang_thai]  DEFAULT ((1)) FOR [nhanvien_trang_thai]
GO
ALTER TABLE [dbo].[tb_nhanvien] ADD  CONSTRAINT [DF_tb_nhanvien_nhanvien_permission]  DEFAULT ((2)) FOR [nhanvien_permission]
GO
ALTER TABLE [dbo].[tb_quanlyduongdi] ADD  CONSTRAINT [DF_tb_quanlyduongdi_quanlyduongdi_vi_do]  DEFAULT (NULL) FOR [quanlyduongdi_vi_do]
GO
ALTER TABLE [dbo].[tb_quanlyduongdi] ADD  CONSTRAINT [DF_tb_quanlyduongdi_quanlyduongdi_kinh_do]  DEFAULT (NULL) FOR [quanlyduongdi_kinh_do]
GO
ALTER TABLE [dbo].[tb_sanpham] ADD  CONSTRAINT [DF_tb_sanpham_sanpham_thue]  DEFAULT ((0)) FOR [sanpham_thue]
GO
ALTER TABLE [dbo].[tb_sanpham] ADD  CONSTRAINT [DF_tb_sanpham_sanpham_gia_nhap]  DEFAULT ((0)) FOR [sanpham_gia_nhap]
GO
ALTER TABLE [dbo].[tb_sanpham] ADD  CONSTRAINT [DF_tb_sanpham_sanpham_gia_ban]  DEFAULT ((0)) FOR [sanpham_gia_ban]
GO
ALTER TABLE [dbo].[tb_sanpham] ADD  CONSTRAINT [DF_tb_sanpham_sanpham_nha_cung_cap]  DEFAULT ('nhacungcap1') FOR [sanpham_nha_cung_cap]
GO
ALTER TABLE [dbo].[tb_schedule] ADD  CONSTRAINT [DF_tb_schedule_schedule_trang_thai]  DEFAULT ((0)) FOR [schedule_trang_thai]
GO
ALTER TABLE [dbo].[tb_thongbao] ADD  CONSTRAINT [DF_tb_thongbao_thongbao_trang_thai]  DEFAULT ((1)) FOR [thongbao_trang_thai]
GO
ALTER TABLE [dbo].[tb_timekeeper] ADD  CONSTRAINT [DF_tb_timekeeper_time_between]  DEFAULT ((0)) FOR [time_between]
GO
ALTER TABLE [dbo].[tb_user] ADD  CONSTRAINT [DF_tb_user_user_permission]  DEFAULT ((2)) FOR [user_permission]
GO
ALTER TABLE [dbo].[tb_user] ADD  CONSTRAINT [DF_tb_user_user_status]  DEFAULT ((1)) FOR [user_status]
GO
ALTER TABLE [dbo].[tb_user] ADD  CONSTRAINT [DF_tb_user_user_pw]  DEFAULT ((123456)) FOR [user_pw]
GO
ALTER TABLE [dbo].[tb_calendar]  WITH CHECK ADD  CONSTRAINT [FK_tb_calendar_tb_nhanvien] FOREIGN KEY([staff_id])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_calendar] CHECK CONSTRAINT [FK_tb_calendar_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_chitietdonbanhang_tb_hoadonbanhang] FOREIGN KEY([chitietdonbanhang_ma_hoa_don])
REFERENCES [dbo].[tb_hoadonbanhang] ([hoadonbanhang_ma_hoa_don])
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] CHECK CONSTRAINT [FK_tb_chitietdonbanhang_tb_hoadonbanhang]
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_chitietdonbanhang_tb_sanpham] FOREIGN KEY([chitietdonbanhang_ma_hang])
REFERENCES [dbo].[tb_sanpham] ([sanpham_ma_san_pham])
GO
ALTER TABLE [dbo].[tb_chitietdonbanhang] CHECK CONSTRAINT [FK_tb_chitietdonbanhang_tb_sanpham]
GO
ALTER TABLE [dbo].[tb_chitietdondathang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_chitietdondathang_tb_hoadondathang] FOREIGN KEY([chitietdondathang_ma_hoa_don])
REFERENCES [dbo].[tb_hoadondathang] ([hoadondathang_ma_hoa_don])
GO
ALTER TABLE [dbo].[tb_chitietdondathang] CHECK CONSTRAINT [FK_tb_chitietdondathang_tb_hoadondathang]
GO
ALTER TABLE [dbo].[tb_chitietdondathang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_chitietdondathang_tb_sanpham] FOREIGN KEY([chitietdondathang_ma_hang])
REFERENCES [dbo].[tb_sanpham] ([sanpham_ma_san_pham])
GO
ALTER TABLE [dbo].[tb_chitietdondathang] CHECK CONSTRAINT [FK_tb_chitietdondathang_tb_sanpham]
GO
ALTER TABLE [dbo].[tb_chitietdontrahang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_chitietdontrahang_tb_sanpham] FOREIGN KEY([chitietdontrahang_ma_hang])
REFERENCES [dbo].[tb_sanpham] ([sanpham_ma_san_pham])
GO
ALTER TABLE [dbo].[tb_chitietdontrahang] CHECK CONSTRAINT [FK_tb_chitietdontrahang_tb_sanpham]
GO
ALTER TABLE [dbo].[tb_forleave]  WITH CHECK ADD  CONSTRAINT [FK_tb_forleave_tb_nhanvien] FOREIGN KEY([staff_id])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_forleave] CHECK CONSTRAINT [FK_tb_forleave_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadonbanhang_tb_khachhang_id_kh] FOREIGN KEY([hoadonbanhang_ma_khach_hang])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] CHECK CONSTRAINT [FK_tb_hoadonbanhang_tb_khachhang_id_kh]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadonbanhang_tb_nhanvien] FOREIGN KEY([hoadonbanhang_nguoi_tao])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] CHECK CONSTRAINT [FK_tb_hoadonbanhang_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_hoadonbanhang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadonbanhang_tb_nhanvien1] FOREIGN KEY([hoadonbanhang_nguoi_sua])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_hoadonbanhang] CHECK CONSTRAINT [FK_tb_hoadonbanhang_tb_nhanvien1]
GO
ALTER TABLE [dbo].[tb_hoadondathang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadondathang_tb_khachhang_id_kh] FOREIGN KEY([hoadondathang_ma_khach_hang])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_hoadondathang] CHECK CONSTRAINT [FK_tb_hoadondathang_tb_khachhang_id_kh]
GO
ALTER TABLE [dbo].[tb_hoadondathang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadondathang_tb_nhanvien] FOREIGN KEY([hoadondathang_nguoi_tao])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_hoadondathang] CHECK CONSTRAINT [FK_tb_hoadondathang_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_hoadondathang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadondathang_tb_nhanvien1] FOREIGN KEY([hoadondathang_nguoi_sua])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_hoadondathang] CHECK CONSTRAINT [FK_tb_hoadondathang_tb_nhanvien1]
GO
ALTER TABLE [dbo].[tb_hoadontrahang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadontrahang_tb_khachhang_id_kh] FOREIGN KEY([hoadontrahang_ma_khach_hang])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_hoadontrahang] CHECK CONSTRAINT [FK_tb_hoadontrahang_tb_khachhang_id_kh]
GO
ALTER TABLE [dbo].[tb_hoadontrahang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadontrahang_tb_nhanvien] FOREIGN KEY([hoadontrahang_nguoi_tao])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_hoadontrahang] CHECK CONSTRAINT [FK_tb_hoadontrahang_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_hoadontrahang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_hoadontrahang_tb_nhanvien1] FOREIGN KEY([hoadontrahang_nguoi_sua])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_hoadontrahang] CHECK CONSTRAINT [FK_tb_hoadontrahang_tb_nhanvien1]
GO
ALTER TABLE [dbo].[tb_khachhang]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_khachhang_tb_nhan_vien_2] FOREIGN KEY([khachhang_ma_nv])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_khachhang] CHECK CONSTRAINT [FK_tb_khachhang_tb_nhan_vien_2]
GO
ALTER TABLE [dbo].[tb_khachhang_hinhanh]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_khachhang_hinhanh_tb_khachhang] FOREIGN KEY([hinhanh_ma_khach_hang])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_khachhang_hinhanh] CHECK CONSTRAINT [FK_tb_khachhang_hinhanh_tb_khachhang]
GO
ALTER TABLE [dbo].[tb_khachhang_hinhanh]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_khachhang_hinhanh_tb_nhanvien] FOREIGN KEY([hinhanh_ma_nhan_vien])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_khachhang_hinhanh] CHECK CONSTRAINT [FK_tb_khachhang_hinhanh_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_kho]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_kho_tb_sanpham] FOREIGN KEY([kho_ma_san_pham])
REFERENCES [dbo].[tb_sanpham] ([sanpham_ma_san_pham])
GO
ALTER TABLE [dbo].[tb_kho] CHECK CONSTRAINT [FK_tb_kho_tb_sanpham]
GO
ALTER TABLE [dbo].[tb_kiemkho]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_kiemkho_tb_khachhang_id_kh] FOREIGN KEY([kiemkho_ma_khach_hang])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_kiemkho] CHECK CONSTRAINT [FK_tb_kiemkho_tb_khachhang_id_kh]
GO
ALTER TABLE [dbo].[tb_kiemkho]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_kiemkho_tb_nhanvien] FOREIGN KEY([kiemkho_nguoi_tao])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_kiemkho] CHECK CONSTRAINT [FK_tb_kiemkho_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_kiemkho]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_kiemkho_tb_nhanvien1] FOREIGN KEY([kiemkho_nguoi_sua])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_kiemkho] CHECK CONSTRAINT [FK_tb_kiemkho_tb_nhanvien1]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_kiemkhochitiet_tb_kiemkho] FOREIGN KEY([kiemkhochitiet_ma_hoa_don])
REFERENCES [dbo].[tb_kiemkho] ([kiemkho_ma_hoa_don])
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] CHECK CONSTRAINT [FK_tb_kiemkhochitiet_tb_kiemkho]
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_kiemkhochitiet_tb_sanpham] FOREIGN KEY([kiemkhochitiet_ma_vach])
REFERENCES [dbo].[tb_sanpham] ([sanpham_ma_san_pham])
GO
ALTER TABLE [dbo].[tb_kiemkhochitiet] CHECK CONSTRAINT [FK_tb_kiemkhochitiet_tb_sanpham]
GO
ALTER TABLE [dbo].[tb_nhanvien]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_nhanvien_tb_Quan_ly] FOREIGN KEY([nhanvien_nguoi_quan_ly])
REFERENCES [dbo].[tb_user] ([user_id])
GO
ALTER TABLE [dbo].[tb_nhanvien] CHECK CONSTRAINT [FK_tb_nhanvien_tb_Quan_ly]
GO
ALTER TABLE [dbo].[tb_quanlyduongdi]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_quanlyduongdi_tb_nhanvien] FOREIGN KEY([quanlyduongdi_ma_nhan_vien])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_quanlyduongdi] CHECK CONSTRAINT [FK_tb_quanlyduongdi_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_quanlygiamdoc]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_quanlygiamdoc_tb_user_ma_giam_doc] FOREIGN KEY([quanlygiamdoc_giamdoc])
REFERENCES [dbo].[tb_user] ([user_id])
GO
ALTER TABLE [dbo].[tb_quanlygiamdoc] CHECK CONSTRAINT [FK_tb_quanlygiamdoc_tb_user_ma_giam_doc]
GO
ALTER TABLE [dbo].[tb_quanlygiamdoc]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_quanlygiamdoc_tb_user_ma_tong_giam_doc] FOREIGN KEY([quanlygiamdoc_tong_giam_doc])
REFERENCES [dbo].[tb_user] ([user_id])
GO
ALTER TABLE [dbo].[tb_quanlygiamdoc] CHECK CONSTRAINT [FK_tb_quanlygiamdoc_tb_user_ma_tong_giam_doc]
GO
ALTER TABLE [dbo].[tb_sanpham]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_sanpham_tb_nha_cung_cap] FOREIGN KEY([sanpham_nha_cung_cap])
REFERENCES [dbo].[tb_nhacungcap] ([nhacungcap_id])
GO
ALTER TABLE [dbo].[tb_sanpham] CHECK CONSTRAINT [FK_tb_sanpham_tb_nha_cung_cap]
GO
ALTER TABLE [dbo].[tb_schedule]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_schedule_makh_khachhang] FOREIGN KEY([schedule_ma_khach_hang])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_schedule] CHECK CONSTRAINT [FK_tb_schedule_makh_khachhang]
GO
ALTER TABLE [dbo].[tb_schedule]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_schedule_manv_nhanvien] FOREIGN KEY([schedule_ma_nv])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_schedule] CHECK CONSTRAINT [FK_tb_schedule_manv_nhanvien]
GO
ALTER TABLE [dbo].[tb_setlunch]  WITH CHECK ADD  CONSTRAINT [FK_tb_setlunch_tb_nhanvien] FOREIGN KEY([staff_id])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_setlunch] CHECK CONSTRAINT [FK_tb_setlunch_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_staffhistory]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_staffhistory_tb_khachhang] FOREIGN KEY([history_customer])
REFERENCES [dbo].[tb_khachhang] ([khachhang_ma_dt])
GO
ALTER TABLE [dbo].[tb_staffhistory] CHECK CONSTRAINT [FK_tb_staffhistory_tb_khachhang]
GO
ALTER TABLE [dbo].[tb_staffhistory]  WITH NOCHECK ADD  CONSTRAINT [FK_tb_staffhistory_tb_nhanvien] FOREIGN KEY([history_staff])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_staffhistory] CHECK CONSTRAINT [FK_tb_staffhistory_tb_nhanvien]
GO
ALTER TABLE [dbo].[tb_timekeeper]  WITH CHECK ADD  CONSTRAINT [FK_tb_timekeeper_tb_nhanvien] FOREIGN KEY([staff_id])
REFERENCES [dbo].[tb_nhanvien] ([nhanvien_ma_nhan_vien])
GO
ALTER TABLE [dbo].[tb_timekeeper] CHECK CONSTRAINT [FK_tb_timekeeper_tb_nhanvien]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'dang ban hang, da duyet, hoan thanh, huy' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_hoadonbanhang', @level2type=N'COLUMN',@level2name=N'hoadonbanhang_trang_thai_don_hang'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'dang dat hang, da duyet, hoan thanh, huy' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_hoadondathang', @level2type=N'COLUMN',@level2name=N'hoadondathang_trang_thai_don_hang'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'dang tra hang, da duyet, hoan thanh, huy' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_hoadontrahang', @level2type=N'COLUMN',@level2name=N'hoadontrahang_trang_thai_don_hang'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'dang dat hang, da duyet, hoan thanh, huy' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'tb_kiemkho', @level2type=N'COLUMN',@level2name=N'kiemkho_trang_thai_don_hang'
GO
