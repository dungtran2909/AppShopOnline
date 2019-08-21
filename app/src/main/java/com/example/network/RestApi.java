package com.example.network;

import android.widget.ScrollView;

import com.example.model.DanhMuc;
import com.example.model.DonHang;
import com.example.model.SanPham;
import com.example.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    @GET("danhmuc/{1}")
    Call<List<DanhMuc>> getDanhMuc();

    @GET("sanpham")
    Call<List<SanPham>> getSanPham();

    //@FormUrlEncoded
    @GET("sanpham")
    Call<List<SanPham>> getListSanPham(@Query("madm") int id);

    @POST("khachhang")
    Call<Boolean> getCreatAccount(@Query("tenkh") String tenKH, @Query("email") String email,
                                  @Query("phone") int phone, @Query("diachi") String diachi,
                                  @Query("password") String password);

    @GET("khachhang")
    Call<List<User>> getListUser();

    @GET("khachhang")
    Call<User> getKhachHangTheoEmail(@Query("email") String email);

    @GET("khachhang/{id}")
    Call<User> getUsertheoMa(@Query("id") int id);

    @GET("donhang")
    Call<List<DonHang>> getListDonHangTheoMaKH(@Query("makh") int makh);

    @POST("khachhang")
    Call<Boolean> getFixAccount(@Query("maKHSua") int maKHSua, @Query("tenKH") String tenKH,
                                @Query("email") String email, @Query("phone") int phone,
                                @Query("diachi")String diachi, @Query("password") String password);

    @POST("khachhang")
    Call<Boolean> getChangePassFromEmail(@Query("emailKHDoiMK") String emailKHDoiMK, @Query("password") String password);

    @POST("donhang")
    Call<Boolean> saveNewDonHang(@Query("maKH") int maKH, @Query("trangThai") int trangThai, @Query("ngayDat") String ngayDat, @Query("ngayGiao") String ngayGiao);

    @GET("donhang")
    Call<List<DonHang>> layDonHangTheoMaKH(@Query("makh") int makh);

    @POST("CTDonHang")
    Call<Boolean> luuMoiCTDonHang(@Query("madh") int madh, @Query("maSP") int maSP, @Query("soLuong") int soLuong);

    @GET("sanpham")
    Call<SanPham> getSPTheoTen(@Query("tenSanPhamTim") String tenSanPhamTim);
}
