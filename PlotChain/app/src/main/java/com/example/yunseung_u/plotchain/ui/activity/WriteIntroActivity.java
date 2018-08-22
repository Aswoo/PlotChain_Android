package com.example.yunseung_u.plotchain.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunseung_u.plotchain.R;
import com.example.yunseung_u.plotchain.application.PlotChainApplication;
import com.example.yunseung_u.plotchain.model.RegisterNovel;
import com.example.yunseung_u.plotchain.model.User;
import com.example.yunseung_u.plotchain.ui.CustomGenreDialog;
import com.example.yunseung_u.plotchain.util.ColorHelper;
import com.example.yunseung_u.plotchain.util.api.BaseApiService;
import com.example.yunseung_u.plotchain.util.api.UtilsApi;
import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yunseung_u.plotchain.util.GenreHelper.switchStringToCode;

public class WriteIntroActivity extends AppCompatActivity {


    private final static int CAMERA_CODE = 100;
    private final static int GALLERY_CODE = 200;


    static public String TAG = "WriteIntroAcitivty";
    private BaseApiService baseApiService;


    private ColorViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.ig_intro_back)
    ImageView itrobackBtn;
    @BindView(R.id.et_intro_title)
    EditText etIntroNovelTitle;
    @BindView(R.id.tv_intro_genre)
    TextView introNovelGenre;
    @BindView(R.id.et_write_intro)
    EditText etIntroWrite;
    @BindView(R.id.vp_intro_image)
    ViewPager viewpager;
    @BindView(R.id.header_view)
    View headerView;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    private String currentColor = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoToolbarThme);
        setContentView(R.layout.activity_write_intro);
        ButterKnife.bind(this);
        initViewpager();
        currentColor = "#575d63";
        baseApiService = UtilsApi.getAPIService();
        pageIndicatorView.setCount(8);
        pageIndicatorView.setSelection(0);

    }

    private void initViewpager() {
        viewPagerAdapter = new ColorViewPagerAdapter();
        viewpager.setAdapter(viewPagerAdapter);
        // set PageChangeListener
        viewpager.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    @OnClick(R.id.ig_intro_back)
    public void onClickBack(){
        finish();
    }


    @OnClick(R.id.tv_intro_genre)
    public void onClickGenre(){
        final CustomGenreDialog addCateDialog = new CustomGenreDialog(this);
        addCateDialog.show();

        addCateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override public void onDismiss(DialogInterface dialog) {
                String addCategoryStr = addCateDialog.getAddGenreStr();
                if(addCategoryStr != null){
                    introNovelGenre.setText(addCategoryStr);
                }
            }
        });


    }

    @OnClick(R.id.btn_confirm)
    public void onClickRegsiter(){

        User user = PlotChainApplication.getCurrentUser();

        int genreCode = switchStringToCode(introNovelGenre.getText().toString());

        RegisterNovel registerNovel = new RegisterNovel(
                etIntroNovelTitle.getText().toString(),
                etIntroWrite.getText().toString(),
                genreCode,currentColor);

        baseApiService.registerNovel(registerNovel,user.getSession()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"Success");
                Intent intent = new Intent(WriteIntroActivity.this,WriteNovelAcitvity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(WriteIntroActivity.this,"업로드 실패",Toast.LENGTH_LONG).show();

            }
        });
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            //addBottomDots(position);

            pageIndicatorView.setSelection(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            String color = ColorHelper.positionToColor(position);
            String bgColor = ColorHelper.backgourndToColor(position);
            currentColor = color;
            viewpager.setBackgroundColor(Color.parseColor(color));
            headerView.setBackgroundColor(Color.parseColor(bgColor));
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private int[] layouts ={
            R.layout.color_0,
            R.layout.color_1,
            R.layout.color_2,
            R.layout.color_3,
            R.layout.color_4,
            R.layout.color_5,
            R.layout.color_6,
            R.layout.color_7
    };

    public class ColorViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;


        public ColorViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
    // end of inner class




}
