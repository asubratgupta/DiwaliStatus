package com.subratgupta.diwalistatus;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<MessageType> messageTypeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageAdapter mAdapter;
    private static ClipboardManager clipboard;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        context = getApplicationContext();


   /*     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MessageAdapter(messageTypeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    public static String randomColor() {
        String color = "000000";
        final int random = new Random().nextInt(8);
        switch (random) {
            case 0:
                color = "#F44336";
                break;
            case 1:
                color = "#E91E63";
                break;
            case 2:
                color = "#9C27B0";
                break;
            case 3:
                color = "#673AB7";
                break;
            case 4:
                color = "#3F51B5";
                break;
            case 5:
                color = "#2196F3";
                break;
            case 6:
                color = "#009688";
                break;
            case 7:
                color = "#FF5722";
                break;
            default:
                color = "795548";

        }
        return color;
    }

    public static void copy(String msg) {
        ClipData clip = ClipData.newPlainText("label", msg);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Message Copied to Clipboard.", Toast.LENGTH_SHORT).show();
    }

    private void prepareMovieData() {
        MessageType message = new MessageType("मैं प्रार्थना करती हूँ भगवान् से कि वो आपको शान्ति, शक्ति, सम्पत्ति, स्वरूप, सयम, सादगी, सफ़लता, समृद्धि, संस्कार, स्वास्थय, सम्मान, सरस्वती और स्नेह दे…. शुभ दीपावली");
        messageTypeList.add(message);

        message = new MessageType("इस दिवाली पर हमारी दुआ है कि आपका हर सपना पूरा हो , दुनिया के ऊँचे मुकाम आपके हो");
        messageTypeList.add(message);

        message = new MessageType("हम आपके लिए खास हो, लक्ष्मी माता का आपके घर में वास हो ॐ महलक्ष्म्यै नमः ॥");
        messageTypeList.add(message);

        message = new MessageType("लक्ष्मी जी विराजें आपके द्वार…सोने चांदी से भर जाए आपका घर-बार..जीवन में आयें खुशियाँ आपार… शुभकामनाएं हमारी करें स्वीकार।");
        messageTypeList.add(message);

        message = new MessageType("॥ ॐ महलक्ष्म्यै नमः ॥");
        messageTypeList.add(message);

        message = new MessageType("Sending you happiness,love and smiles from the deepest depth of my heart. A very happy and prosperous Diwali.");
        messageTypeList.add(message);

        message = new MessageType("दीयों की रौशनी से झिलमिलाता आँगन हो ,\n" +
                "पटाख़ों की गूँजो से आसमान रोशन हो ,\n" +
                "ऐसे आये झूम के यह दिवाली ,\n" +
                "हर तरफ खुशियों का मौसम हो।\n");
        messageTypeList.add(message);

        message = new MessageType("॥ 卐 शुभ दीपावली 卐॥");
        messageTypeList.add(message);

        message = new MessageType("दीपावली के शुभ अवसर पर याद आपकी आए,शब्द शब्द जोड़ कर देते तुम्हें बधाई।");
        messageTypeList.add(message);

        message = new MessageType("दीपावली में दीयों का दीदार, बड़ों का दुलार और सबको प्यार॥ Happy Diwali");
        messageTypeList.add(message);

        message = new MessageType("देवी महालक्ष्मी की कृपा से…आपके घर में हमेशा उमंग और आनंद की रौनक हो..इस पावन मौके पर आप सबको दिवाली की हार्दिक शुभकामनाएँ॥");
        messageTypeList.add(message);

        message = new MessageType("Your Eyes Patakha, your Lips Rocket,\n" +
                "Your Ears Chakari, your Nose Fuljari,\n" +
                "Your Style Anaar, your Personality Bomb,\n" +
                "SMS Karo Varna I’m Coming With Agarbatti");
        messageTypeList.add(message);

        mAdapter.notifyDataSetChanged();
    }
}
