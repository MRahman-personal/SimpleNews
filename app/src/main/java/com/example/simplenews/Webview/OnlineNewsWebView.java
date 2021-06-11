package com.example.simplenews.Webview;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.simplenews.Database.ArticleEntity;
import com.example.simplenews.R;

public class OnlineNewsWebView extends AppCompatActivity {

    private WebView webView;
    private OnlineNewsWebViewModel mViewModel;
    private ArticleEntity articleEntity;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("image_url");
        String publishedAt = intent.getStringExtra("published");
        Application application = getApplication();
        url = intent.getStringExtra("url");
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(OnlineNewsWebViewModel.class);
        webView = findViewById(R.id.web_view);
        articleEntity = new ArticleEntity(title, description, url, imageUrl, publishedAt);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewModel.getUrl().observe(this, newsWebViewObserver);
        mViewModel.addUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_button:
                try {
                    mViewModel.addArticle(articleEntity);
                    Toast.makeText(OnlineNewsWebView.this, "Article saved", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(OnlineNewsWebView.this, "Article already exists in saved stories", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.share_button:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Story");
                    String shareMessage = url;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Choose one"));
                } catch(Exception e) {
                    Toast.makeText(OnlineNewsWebView.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    Observer<String> newsWebViewObserver = new Observer<String>() {
        @Override
        public void onChanged(String modelUrl) {
            webView.loadUrl(modelUrl);
        }
    };

}