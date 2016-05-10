package work.beltran.discogsbrowser.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import work.beltran.discogsbrowser.R;

public class AboutActivity extends AppCompatActivity {
    LinearLayout listView;
    private List<Library> libraries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("About");
        }
        listView = (LinearLayout) findViewById(R.id.listTechnicalInfo);

        libraries = new ArrayList<>();
        libraries.add(new Library("org.mockito", "1.10.19", "http://mockito.org/"));
        libraries.add(new Library("org.robolectric", "3.1-SNAPSHOT", "http://robolectric.org/"));
        libraries.add(new Library("com.google.dagger", "2.0", "http://google.github.io/dagger/"));
        libraries.add(new Library("com.eyemm.recyclerviewtools", "0.0.3-SNAPSHOT", "https://github.com/eyeem/RecyclerViewTools"));
        libraries.add(new Library("io.reactivex.rxjava", "1.1.2", "https://github.com/ReactiveX/RxJava"));
        libraries.add(new Library("com.jakewhartorn.rxbinding", "0.4.0", "https://github.com/JakeWharton/RxBinding"));
        libraries.add(new Library("com.squareup.retrofit2", "2.0.0-beta3", "http://square.github.io/retrofit/"));
        libraries.add(new Library("com.aurelhubert:ahbottomnavigation", "1.1.8", "https://github.com/aurelhubert/ahbottomnavigation"));
        libraries.add(new Library("org.assertj", "1.7.1", "http://joel-costigliola.github.io/assertj/"));

        for (final Library library : libraries) {
            View view = View.inflate(this, android.R.layout.simple_list_item_2, null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = library.url;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);
            text1.setText(library.name);
            text2.setText(library.url);
            listView.addView(view);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","miquelbeltran@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Vinyl Discogs Browser App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void onClickWeb(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://beltran.work"));
        startActivity(i);
    }

    private class Library {
        public final String name;
        public final String version;
        public final String url;

        private Library(String name, String version, String url) {
            this.name = name;
            this.version = version;
            this.url = url;
        }
    }
}
