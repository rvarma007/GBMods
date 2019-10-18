package com.gbmods;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gbmods.gbmods.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GBMods.init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        GBMods.AddSubMenu(menu);//Add This Code Below invoke-interface {v0, v6}, Landroid/view/MenuItem;->setShowAsAction(I)V in Lcom/whatsapp/HomeActivity
        GBMods.addmenu(menu);//Add This Code Below invoke-interface {v0, v6}, Landroid/view/MenuItem;->setShowAsAction(I)V in Lcom/whatsapp/HomeActivity
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        GBMods.onOptionsItemSelected(item, this);//Add This Code Above invoke-super {p0, p1}, Lcom/whatsapp/DialogToastActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z in Lcom/whatsapp/HomeActivity
        return super.onOptionsItemSelected(item);
    }
}
