package pro.alexzaitsev.freepager.sample;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

public class AboutActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.layout_github_project).setOnClickListener(this);
        findViewById(R.id.layout_github_me).setOnClickListener(this);
        findViewById(R.id.layout_site_me).setOnClickListener(this);
        findViewById(R.id.layout_google_plus_me).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String url = null;
        if (id == R.id.layout_github_project) {
            url = getString(R.string.github_project_link);
        } else if (id == R.id.layout_github_me) {
            url = getString(R.string.github_me_link);
        } else if (id == R.id.layout_site_me) {
            url = getString(R.string.site_me_link);
        } else if (id == R.id.layout_google_plus_me) {
            url = getString(R.string.google_plus_me_link);
        }
        if (!TextUtils.isEmpty(url)) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            try {
                startActivity(i);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
