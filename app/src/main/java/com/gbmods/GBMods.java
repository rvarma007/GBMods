package com.gbmods;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import com.whatsapp.Conversation;

public class GBMods {
    static String JID;
    public static Context ctx;
    public static SharedPreferences mSharedPreferences;
    public static Editor mEditor;
    public static String mString;

    public static void AddSubMenu(Menu menu) {  //Merge This Codes in Lcom/whatsapp/HomeActivity
        SubMenu addSubMenu = menu.addSubMenu(1, 0, 0, getResID("settings_privacy", "string"));
        addSubMenu.add(2, getResID("menu_hideseen", "id"), 0, SetSeenString());
        addSubMenu.add(2, getResID("menu_antirevoke", "id"), 0, SetAR());
        addSubMenu.add(2, getResID("menu_hidestatus", "id"), 0, SetStatus());
        addSubMenu.add(2, getResID("menu_BOR", "id"), 0, SetBOR());
        addSubMenu.add(2, getResID("hidebluetick", "id"), 0, getResID("blueticks", "string"));
        addSubMenu.add(2, getResID("menu_hide2tick", "id"), 0, getResID("h2ticks", "string"));
        addSubMenu.add(2, getResID("composing", "id"), 0, getResID("composing", "string"));
        addSubMenu.add(2, getResID("recording", "id"), 0, getResID("recording", "string"));
        addSubMenu.add(2, getResID("play", "id"), 0, getResID("playing", "string"));
    }

    public static int getResID(final String id, final String type) {
        return ctx.getResources().getIdentifier(id, type, ctx.getPackageName());
    }

    public static boolean AntiRevoke() {
        String str = mString;
        if (!getBool(str)) {
            return getBool("Antirevoke");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_AR");
        return getBool(stringBuilder.toString());
    }

    private static String GetType2(String str) {
        return str.contains("g.us") ? "G" : str.contains("broadcast") ? "B" : str.contains("s.whatsapp.net") ? "C" : "ST";
    }

    public static boolean HideCR(String str, int i) {
        String str2;
        StringBuilder stringBuilder;
        if (i == 1) {
            str2 = "_HideRecord";
            if (getBool(str)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(str2);
                return getBool(stringBuilder.toString());
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(GetType2(str));
            stringBuilder.append(str2);
            getBool(stringBuilder.toString());
            return false;
        }
        str2 = "_HideCompose";
        if (getBool(str)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            return getBool(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(GetType2(str));
        stringBuilder.append(str2);
        return getBool(stringBuilder.toString());
    }

    public static boolean HidePlay() {
        String str = mString;
        StringBuilder stringBuilder;
        String str2 = "_HidePlay";
        if (getBool(str)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            return getBool(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(GetType2(str));
        stringBuilder.append(str2);
        return getBool(stringBuilder.toString());
    }

    public static boolean HideRead(Object obj) {
        String str = (String) obj;
        StringBuilder stringBuilder;
        String str2 = "_HideRead";
        if (getBool(str)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            return getBool(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(GetType2(str));
        stringBuilder.append(str2);
        return getBool(stringBuilder.toString());
    }

    public static boolean HideReceipt() {
        String str = mString;
        StringBuilder stringBuilder;
        String str2 = "_HideReceipt";
        if (getBool(str)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            return getBool(stringBuilder.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(GetType2(str));
        stringBuilder.append(str2);
        return getBool(stringBuilder.toString());
    }

    public static boolean HideSeen() {
        return getBool("HideSeen");
    }

    public static boolean HideStatus() {
        String str = JID;
        if (!getBool(str)) {
            return getBool("hide_status");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_HideStatus");
        return getBool(stringBuilder.toString());
    }

    private static void IsHide2Ticksdialog(final Context context) {
        Builder builder = new Builder(context);
        builder.setCancelable(false);
        builder.setTitle(getResID("alert", "string"));
        builder.setMessage(getResID("alertmsg", "string"));
        builder.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GBMods.restartApp(context);
            }
        });
        builder.create().show();
    }

    public static void MenuBlueTicks(final Context context) {
        CharSequence[] charSequenceArr = new String[]{getString(context, SetBlueMenuString()), getString(context, SetBlueStringGroup())};
        Builder builder = new Builder(context);
        builder.setItems(charSequenceArr, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    GBMods.setHideBlueTicks(context);
                } else if (i == 1) {
                    GBMods.setHideBlueTicksGroup(context);
                }
            }
        });
        builder.create();
        builder.show();
    }

    public static void MenuComposing(final Context context) {
        CharSequence[] charSequenceArr = new String[]{getString(context, SetComposingMenuString()), getString(context, SetComposingStringGroup())};
        Builder builder = new Builder(context);
        builder.setItems(charSequenceArr, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    GBMods.setHideComposing(context);
                } else if (i == 1) {
                    GBMods.setHideComposingGroup(context);
                }
            }
        });
        builder.create();
        builder.show();
    }

    public static void MenuPlay(final Context context) {
        CharSequence[] charSequenceArr = new String[]{getString(context, SetPlayMenuString()), getString(context, SetPlayStringGroup())};
        Builder builder = new Builder(context);
        builder.setItems(charSequenceArr, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    GBMods.setHidePlay(context);
                } else if (i == 1) {
                    GBMods.setHidePlayGroup(context);
                }
            }
        });
        builder.create();
        builder.show();
    }

    public static void MenuRecord(final Context context) {
        CharSequence[] charSequenceArr = new String[]{getString(context, SetRecordMenuString()), getString(context, SetRecordStringGroup())};
        Builder builder = new Builder(context);
        builder.setItems(charSequenceArr, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    GBMods.setHideRecord(context);
                } else if (i == 1) {
                    GBMods.setHideRecordGroup(context);
                }
            }
        });
        builder.create();
        builder.show();
    }

    static Intent OpenChat(String str) {
        try {
            return a(ctx, str);
        } catch (Exception e) {
            return null;
        }
    }

    private static int Set2MenuString() {
        return getBool("C_HideReceipt") ? getResID("showdouble", "string") : getResID("hidedouble", "string");
    }

    private static int Set2ticksStringGroup() {
        return getBool("G_HideReceipt") ? getResID("showdoubleg", "string") : getResID("hidedoubleg", "string");
    }

    public static int SetAR() {
        return getBool("Antirevoke") ? getResID("disablear", "string") : getResID("enablear", "string");
    }

    public static int SetBOR() {
        return getBool("BlueOnReply") ? getResID("showBOR", "string") : getResID("hidebor", "string");
    }

    private static int SetBlueMenuString() {
        return getBool("C_HideRead") ? getResID("showblue", "string") : getResID("hideblue", "string");
    }

    private static int SetBlueStringGroup() {
        return getBool("G_HideRead") ? getResID("showblueg", "string") : getResID("hideblueg", "string");
    }

    private static int SetComposingMenuString() {
        return getBool("C_HideCompose") ? getResID("showcompose", "string") : getResID("hidecompose", "string");
    }

    private static int SetComposingStringGroup() {
        return getBool("G_HideCompose") ? getResID("showcomposeg", "string") : getResID("hidecomposeg", "string");
    }

    public static void SetJID(String str) {
        JID = str;
    }

    private static int SetPlayMenuString() {
        return getBool("C_HidePlay") ? getResID("showplay", "string") : getResID("hideplay", "string");
    }

    private static int SetPlayStringGroup() {
        return getBool("G_HidePlay") ? getResID("showplayg", "string") : getResID("hideplayg", "string");
    }

    private static int SetRecordMenuString() {
        return getBool("C_HideRecord") ? getResID("showrecord", "string") : getResID("hiderecord", "string");
    }

    private static int SetRecordStringGroup() {
        return getBool("G_HideRecord") ? getResID("showrecordg", "string") : getResID("hiderecordg", "string");
    }

    public static int SetSeenString() {
        return getBool("HideSeen") ? getResID("showls", "string") : getResID("hidels", "string");
    }

    public static int SetStatus() {
        return getBool("hide_status") ? getResID("showstatus", "string") : getResID("hidestatus", "string");
    }

    @SuppressLint("WrongConstant")
    public static Intent a(Context context, String str) {
        return new Intent(context, Conversation.class).putExtra("jid", str).addFlags(335544320);
    }

    public static void addmenu(Menu menu) {   //Merge This Codes in Lcom/whatsapp/HomeActivity
        menu.add(1, getResID("restart", "id"), 0, getResID("restart", "string"));
        menu.add(1, getResID("openc", "id"), 0, getResID("openchat", "string"));
    }

    public static boolean getBool(String str) {
        return mSharedPreferences.getBoolean(str, false);
    }

    public static String getString(Context context, int i) {
        return VERSION.SDK_INT >= 23 ? context.getString(i) : context.getResources().getString(i);
    }

    public static void init(Context context) {
        //Status.T = new HashMap();
        mSharedPreferences = context.getSharedPreferences("GBMods", 0);
        mEditor = mSharedPreferences.edit();
        ctx = context;
    }

    public static void onOptionsItemSelected(MenuItem menuItem, final Context context) {   //Merge This Codes in Lcom/whatsapp/HomeActivity
        int item = menuItem.getItemId();
        if (item == getResID("menu_hideseen", "id")) {
            setHideSeen(context);
        } else if (item == getResID("menu_antirevoke", "id")) {
            setAntiRevoke(context);
        } else if (item == getResID("menu_hidestatus", "id")) {
            setStatusView(context);
        } else if (item == getResID("menu_BOR", "id")) {
            setBOR(context);
        } else if (item == getResID("menu_hide2tick", "id")) {
            sethide2tick(context);
        } else if (item == getResID("hidebluetick", "id")) {
            MenuBlueTicks(context);
        } else if (item == getResID("composing", "id")) {
            MenuComposing(context);
        } else if (item == getResID("recording", "id")) {
            MenuRecord(context);
        } else if (item == getResID("play", "id")) {
            MenuPlay(context);
        } else if (item == getResID("restart", "id")) {
            restartApp(context);
        } else if (item == getResID("openc", "id")) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            mBuilder.setTitle("Message a Number");
            mBuilder.setMessage("Enter Phone Number");
            final EditText mNumberInput = new EditText(context);
            mNumberInput.setHint("Ex: 91XXXXXXXXXX");
            mNumberInput.setHintTextColor(-3355444);
            mNumberInput.setBackgroundColor(-1);
            mNumberInput.setTextColor(-16777216);
            mNumberInput.setInputType(3);
            mBuilder.setView(mNumberInput);
            mBuilder.setPositiveButton("Message",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String mNumber = mNumberInput.getText().toString().trim().replace("+", "").replace(" ", "");
                            String str = mNumber + "@s.whatsapp.net";
                            if (mNumber.contains("-")) {
                                str = mNumber + "@g.us";
                            }
                            context.startActivity(OpenChat(str));
                        }
                    });
            mBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            mBuilder.show();
        }
    }

    public static void restartApp(final Context mContext) {
        ((AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE))
                .set(AlarmManager.RTC, 100L + System.currentTimeMillis(),
                 PendingIntent.getActivity(mContext, 123456, mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName()),
                 PendingIntent.FLAG_CANCEL_CURRENT));
        System.exit(0);
    }

    public static void setAntiRevoke(Context context) {
        String str = "Antirevoke";
        setBoolean(str, getBool(str));
        restartApp(context);
    }

    public static void setBOR(Context context) {
        String str = "BlueOnReply";
        setBoolean(str, getBool(str));
        restartApp(context);
    }

    public static void setBoolean(String str, boolean z) {
        mEditor.putBoolean(str, z).commit();
    }

    public static void setHide2Ticks(Context context) {
        String str = "C_HideReceipt";
        setBoolean(str, getBool(str));
        IsHide2Ticksdialog(context);
    }

    public static void setHide2TicksGroup(Context context) {
        String str = "G_HideReceipt";
        if (getBool(str)) {
            setBoolean(str, false);
            return;
        }
        setBoolean(str, true);
        IsHide2Ticksdialog(context);
    }

    private static void setHideBlueTicks(Context context) {
        String str = "C_HideRead";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHideBlueTicksGroup(Context context) {
        String str = "G_HideRead";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHideComposing(Context context) {
        String str = "C_HideCompose";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHideComposingGroup(Context context) {
        String str = "G_HideCompose";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHidePlay(Context context) {
        String str = "C_HidePlay";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHidePlayGroup(Context context) {
        String str = "G_HidePlay";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHideRecord(Context context) {
        String str = "C_HideRecord";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    private static void setHideRecordGroup(Context context) {
        String str = "G_HideRecord";
        if (getBool(str)) {
            setBoolean(str, false);
        } else {
            setBoolean(str, true);
        }
        restartApp(context);
    }

    public static void setHideSeen(Context context) {
        String str = "HideSeen";
        setBoolean(str, getBool(str));
        restartApp(context);
    }

    public static void setStatusView(Context context) {
        String str = "hide_status";
        setBoolean(str, getBool(str));
        restartApp(context);
    }

    public static void setStrin(String str) {
        mString = str;
    }

    public static void sethide2tick(final Context context) {
        try {
            CharSequence[] charSequenceArr = new String[]{getString(context, Set2MenuString()), getString(context, Set2ticksStringGroup())};
            Builder builder = new Builder(context);
            builder.setItems(charSequenceArr, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        GBMods.setHide2Ticks(context);
                    } else if (i == 1) {
                        GBMods.setHide2TicksGroup(context);
                    }
                }
            });
            builder.create();
            builder.show();
        } catch (Exception e) {
        }
    }
}
