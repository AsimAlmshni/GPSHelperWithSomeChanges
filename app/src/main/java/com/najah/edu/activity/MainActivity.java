package com.najah.edu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pranay.rxloginsignup.R;
import com.najah.edu.DatABasE.DB_helper;
import com.najah.edu.DatABasE.value;
import com.najah.edu.fragment.LoginFragment;
import com.najah.edu.fragment.SignUpFragment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    public String get_e ;
    public String get_p;
    public String get_f;
    public String get_l;
    public String get_ph;
    private Locale locale ;

    private DB_helper bb ;

//    EditText getemail = (EditText) findViewById(R.id.edt_email1)  ;
 //   EditText getpass = (EditText) findViewById(R.id.edt_password1) ;

    public value cc;

    @BindView(R.id.viewpager_login_signup)
    ViewPager viewpager_login_signup;
    @BindView(R.id.tablayout_login_signup)
    TabLayout tablayout_login_signup;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        bb = new DB_helper(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                startActivity(i);
//            }
//        });

        setViewPager();
    }


    /**
     * Set Viewpager with two fragments
     */
    private void setViewPager() {
        viewpager_login_signup.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),this));
        tablayout_login_signup.setupWithViewPager(viewpager_login_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    public void setLocale(String lang) {
//        Locale myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//        Intent refresh = new Intent(this, MainActivity.class);
//        startActivity(refresh);
//        finish();
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                setLocal("en");
                Toast.makeText(this, "Locale in English !", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_shareAr:
                setLocal("ar");
                Toast.makeText(this, "Locale in Arabic !", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);

    }

    public void setLocal(String lang) {
        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }


    public void onClickHandler(View view) {

       // String email = getemail.getText().toString().trim();
       // String pass = getpass.getText().toString().trim();
        /*Bundle bundel = getIntent().getExtras();
        String email = bundel.getString("EEEmail");
        String pass = bundel.getString("PPPass");*/

        /*EditText em = (EditText)view.findViewById(R.id.edt_email1) ;
        EditText ps = (EditText)view.findViewById(R.id.edt_password1) ;
        String get_email = em.getText().toString().trim();
        String get_pass = ps.getText().toString().trim();*/

        String get_email = ((EditText) findViewById(R.id.edt_email1)).getText().toString();
        String get_pass = ((EditText) findViewById(R.id.edt_password1)).getText().toString();
        //Toast.makeText(getBaseContext(), get_email + "     " + get_pass, Toast.LENGTH_LONG).show();
       if (bb.check_email_and_pass(get_email  , get_pass)) {
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        } else {
            // Snack Bar to show success message that record is wrong
            Toast.makeText(getBaseContext(), getString(R.string.str_login_error), Toast.LENGTH_LONG).show();
        }
    }


    /**
     *  Pager adapter to set fragment in view pager
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private int PAGE_COUNT=2;
        private String tabTitles[] = new String[]{getString(R.string.str_login), getString(R.string.str_signup)};
        private Context mContext;

        public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return LoginFragment.getInstant();
                case 1:
                    return SignUpFragment.getInstant();
                default:
                    return LoginFragment.getInstant();
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}
