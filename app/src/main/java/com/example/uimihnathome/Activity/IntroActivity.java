package com.example.uimihnathome.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.model.SanPham;
import com.example.sharedpreferences.SharedPreferencesManager;
import com.example.uimihnathome.LoginActivity;
import com.example.uimihnathome.MainActivity;
import com.example.uimihnathome.R;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends FancyWalkthroughActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FancyWalkthroughCard fancywalkthroughCard1 = new FancyWalkthroughCard("Mua Sắm Online", "Có thể mua sắm các loại mặt hàng, dạo quanh các thiết bị công nghệ trên smartphone, xem thông tin sản phẩm",R.drawable.dungtrangneed1);
        FancyWalkthroughCard fancywalkthroughCard2 = new FancyWalkthroughCard("Món Đồ Công Nghê Bắt Mắt", "Các món đồ công nghệ đa dạng, nhiều mẫu mã và các loại mặt hàng",R.drawable.dungtranneed2);
        FancyWalkthroughCard fancywalkthroughCard3 = new FancyWalkthroughCard("Đặt Hàng Trực Tuyến", " Xem review cảu từng sản phẩm Online ngay trên App.",R.drawable.ic_youtube_sing);
        FancyWalkthroughCard fancywalkthroughCard4 = new FancyWalkthroughCard("Tìm Kiếm Dễ Dàng", "Tìm kiếm theo sản phẩm, tên sản phẩm, loại sản phẩm... .",R.drawable.ic_maps);

        fancywalkthroughCard1.setBackgroundColor(R.color.white);
        fancywalkthroughCard1.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard2.setBackgroundColor(R.color.white);
        fancywalkthroughCard2.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard3.setBackgroundColor(R.color.white);
        fancywalkthroughCard3.setIconLayoutParams(300,300,0,0,0,0);
        fancywalkthroughCard4.setBackgroundColor(R.color.white);
        fancywalkthroughCard4.setIconLayoutParams(300,300,0,0,0,0);
        List<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(fancywalkthroughCard1);
        pages.add(fancywalkthroughCard2);
        pages.add(fancywalkthroughCard3);
        pages.add(fancywalkthroughCard4);

        for (FancyWalkthroughCard page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.black);
        }
        setFinishButtonTitle("Bắt đầu nào");
        showNavigationControls(true);
        setColorBackground(R.color.backgroundtoobar);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.backgroundtoobar);
        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        SharedPreferencesManager.setFirstTimeSetup(false);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
