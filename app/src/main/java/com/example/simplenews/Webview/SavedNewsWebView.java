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
import com.example.simplenews.R;

public class SavedNewsWebView extends AppCompatActivity {

    private WebView webView;
    private SavedNewsWebViewModel mViewModel;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        Application application = getApplication();
        url = intent.getStringExtra("entity_url");
        mViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(SavedNewsWebViewModel.class);
        webView = findViewById(R.id.web_view);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewModel.getUrl().observe(this, newsWebViewObserver);
        mViewModel.addUrl(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_button:
                mViewModel.deleteArticle(url);
                Toast.makeText(SavedNewsWebView.this, "Article deleted", Toast.LENGTH_SHORT).show();
                finish();

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
                    Toast.makeText(SavedNewsWebView.this, "An error occurred", Toast.LENGTH_SHORT).show();
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
