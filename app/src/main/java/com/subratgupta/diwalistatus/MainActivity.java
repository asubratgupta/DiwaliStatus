package com.subratgupta.diwalistatus;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<MessageType> messageTypeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageAdapter mAdapter;
    private static ClipboardManager clipboard;
    private static Context context;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MessageAdapter(messageTypeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMessageData();

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        adShow();
    }

    private void adShow() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else if (!mInterstitialAd.isLoading()) {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    Toast.makeText(getApplicationContext(), "Ad is not ready", Toast.LENGTH_SHORT).show();
                }
                adShow();
            }
        }, 30 * 1000);
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

    public static void wa_share(String msg) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, msg);
        try {
            context.startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.context, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void copy(String msg) {
        ClipData clip = ClipData.newPlainText("label", msg);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Message Copied to Clipboard.", Toast.LENGTH_SHORT).show();
    }

    public static void share(String msg) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Diwali Status:\n");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, msg);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void prepareMessageData() {
        MessageType message = new MessageType("“इस दीपावली से अगली दीपावली तक आपको\n" +
                "घरवाली,\n" +
                "बाहरवाली,\n" +
                "कॉलेजवाली,\n" +
                "सब्जीवाली,\n" +
                "दूधवाली,\n" +
                "कामवाली और\n" +
                "फूलवाली, सबका प्यार मिले!\n" +
                "और घरवाली इस सबसे बेखबर रहे!\n" +
                "दीपावली की प्यार भरी शुभ कामनाएं!”");
        messageTypeList.add(message);

        message = new MessageType("लक्ष्मी जी विराजें आपके द्वार…सोने चांदी से भर जाए आपका घर-बार..जीवन में आयें खुशियाँ आपार… शुभकामनाएं हमारी करें स्वीकार।");
        messageTypeList.add(message);

        message = new MessageType("Your Eyes Patakha, your Lips Rocket,\n" +
                "Your Ears Chakari, your Nose Fuljari,\n" +
                "Your Style Anaar, your Personality Bomb,\n" +
                "SMS Karo Varna I’m Coming With Agarbatti");
        messageTypeList.add(message);

        message = new MessageType("दीपावली में दीयों का दीदार, बड़ों का दुलार और सबको प्यार॥ Happy Diwali");
        messageTypeList.add(message);

        message = new MessageType("देवी महालक्ष्मी की कृपा से…आपके घर में हमेशा उमंग और आनंद की रौनक हो..इस पावन मौके पर आप सबको दिवाली की हार्दिक शुभकामनाएँ॥");
        messageTypeList.add(message);

        message = new MessageType("॥ दीवाली की ढेरो शुभकामनाएं ॥");
        messageTypeList.add(message);

        message = new MessageType("क्या भरोसा… मोबाईल का, बैटरी का, चार्जर का, नेटवर्क का, बेलेन्स का, इन्टरनेट का, लाईफ का, टाईम का, इसलिए Advance में Happy Diwali…");
        messageTypeList.add(message);

        message = new MessageType("तू पटाखा है किसी और का, तुझे फोड़ता कोई और है।");
        messageTypeList.add(message);

        message = new MessageType("दीपोत्सव पर ढेरो शुभकामनाएं ॥");
        messageTypeList.add(message);

        message = new MessageType("Aai aai Diwali aai, Saath me kitni Khushiya laayi, Dhoom machao, mauj manao, aap sabhi ko Diwali ki badhai. Happy Diwali");
        messageTypeList.add(message);

        message = new MessageType("“दीवाली के इस मंगल अवसर पर ,आप सभी की मनोकामना पूरी हों, खुशियाँ आपके कदम चूमे ,इसी कामना के साथ आप सभी को दिवाली की ढेरों बधाई .”");
        messageTypeList.add(message);

        message = new MessageType("सुख संपत्ति घर आवे.. शुभ दीपावली।");
        messageTypeList.add(message);

        message = new MessageType("हरदम खुशियाँ हो साथ, कभी दामन ना हो खाली, हम सभी की तरफ से, आपको.. शुभ दीपावली!");
        messageTypeList.add(message);

        message = new MessageType("खूब मीठे मीठे पकवान खाएं, सेहत मैं चार चाँद लगायें, लोग तो सिर्फ चाँद तक गए हैं, आप उस से भी ऊपर जाएँ, दीवाली की शुभकामनायें।");
        messageTypeList.add(message);

        message = new MessageType("खुशियाँ हों overflow, मस्ती कभी न हों low, दोस्ती का सरुर छाया रहे, ऐसा आये आपके लिए दिवाली का त्यौहार।");
        messageTypeList.add(message);

        message = new MessageType("आपको और आपके समस्त परिवार को दीपावली के पावन पर्व पर हमारी ओर से हार्दिक शुभकामनाएँ!!!");
        messageTypeList.add(message);

        message = new MessageType("जगमग जगमग दीप जल उठे, द्वार द्वार आए दीपावली। दीपावली के इस पावन अवसर पर आपको एवं आपके परिवार को हार्दिक शुभकामनाएँ।");
        messageTypeList.add(message);

        message = new MessageType("इस दिवाली पर हमारी दुआ है कि आपका हर सपना पूरा हो , दुनिया के ऊँचे मुकाम आपके हो");
        messageTypeList.add(message);

        message = new MessageType("हम आपके लिए खास हो, लक्ष्मी माता का आपके घर में वास हो ॐ महलक्ष्म्यै नमः ॥");
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

        message = new MessageType("दीवाली के इस मंगल अवसर पर, आप सभी की मनोकामना पूरी हों, खुशियाँ आपके कदम चूमे, इसी कामना के साथ आप सभी को दिवाली की ढेरों बधाई.");
        messageTypeList.add(message);

        message = new MessageType("आई आई दिवाली आई, साथ माँ कितनी खुशियाँ लायी, धूम मचाओ मौज मनाओ, आप सभी को दिवाली की बधाई. शुभ दीवाली।");
        messageTypeList.add(message);

        message = new MessageType("दीपक का पर्काश हर पल आपके जीवन मैं एक नयी रौशनी दे, बस यही शुभकामना है हमारी आपके लिए दीवाली के इस पवन अवसर पर .. शुभ दीवाली");
        messageTypeList.add(message);

        message = new MessageType("है रौशनी का त्यौहार, लाये हर चेहरे पर मुस्कान, सुख और समृधि की बहार, समेट लो सारी खुशियाँ, अपनों का साथ और प्यार.. शुभ दीवाली।");
        messageTypeList.add(message);

        message = new MessageType("झिलमिलते दीपों की आभा से प्रकाशित ये दीवाली आपके घर आँगन में, धन धान्य सुख सम्रिधि और ईश्वर का अन्नत आशीर्वाद लेकर आए..!! Happy Diwali");
        messageTypeList.add(message);

        message = new MessageType("आपका एवें आपके परिवार का हर दिन हर पल शुभ हो, और आप उत्तरोत्तर प्रगति पथ पर अग्रसर रहे, दीपावली महापर्व पर ऐसी शुभकामनाये !!!!!!");
        messageTypeList.add(message);

        message = new MessageType("आँखो से आँसूओं की जुदाई कर दो, दिल से ग़मों की विदाई कर दो, अगर दिल ना लगे कहीं तो, आ जाओ मेरे घर.. और मेरे घर की सफाई कर दो… और याद रहे यह Offer दिवाली तक ही है");
        messageTypeList.add(message);

        message = new MessageType("बुरा ना मानो होली है… यह कह कर किसीने मुझ पर रंग फेंक दिया था… आज ‘बुरा ना मानो दिवाली है’ यह कहकर मैंने उस पर **बम** फेंक दिया… आज पूरा मोहल्ला मुझे ढूंढ रहा है…");
        messageTypeList.add(message);

        message = new MessageType("Deepawali mein deepo ka didar, Khusiyo ke sath mubarak hajar.");
        messageTypeList.add(message);

        message = new MessageType("Har ghar mein ho ujaala, aaye naa kabhi raat kali, Har ghar me mane khushiya, har ghar me ho Diwali.");
        messageTypeList.add(message);

        message = new MessageType("Diye ki roshni se sab andhera dur ho jaye, Dua h ki jo chaho wo khusi manjur ho jaye.");
        messageTypeList.add(message);

        message = new MessageType("Diwali Ki Light, Kare Sab Ko Delight, Pakro Masti Ki Flight Aur Dhoom Machao All Night.. Happy Diwali!!");
        messageTypeList.add(message);

        message = new MessageType("शेर छुपकर ‘शिकार’ नहीं करते; अपने कभी खुलकर ‘वार’ नहीं करते; ‘हम’ वो “”किंग हैं”” जो हैप्पी दिवाली कहने के लिए; दिवाली के दिन का ‘इंतज़ार’ नहीं करते।\n" +
                "हैप्पी दिवाली!”");
        messageTypeList.add(message);

        message = new MessageType("“है रौशनी का त्यौहार, लाये हर चेहरे पर मुस्कान,सुख और समृधि की बहार ,समेट लो सारी खुशियाँ, अपनों का साथ और प्यार..इस पावन अवसर पर आप सभी को दीवाली का प्यार..”");
        messageTypeList.add(message);

        message = new MessageType("“ये रोशनी का पर्व है दीप तुम जलाना; जो हर दिल को अच्छा लगे ऐसा गीत तुम गाना; दुःख दर्द सारे भूलकर सबको गले लगाना; ईद हो या हो दिवाली बस खुशियों से मनाना।\n" +
                "हैप्पी दिवाली!”");
        messageTypeList.add(message);

        message = new MessageType("“जगमग थाली सजाओ,मंगल दीपो को जलाओ,अपने घरों और दिलों मैं आशा की किरण जगाओ, खुशियाँ और समृधि से भरा हों आपका जीवन,इसी कामना के साथ शुभ दीपावली”");
        messageTypeList.add(message);

        message = new MessageType("“दीपक की रोशनी, मिठाइयों की मिठास; पटाको की बौछार, धन की बरसात; हरपल हर दिन आपके लिए लाये, ‘दीपावली’ का त्योहार!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);

        message = new MessageType("“आई आई दिवाली आई,साथ माँ कितनी खुशियाँ लायी, धूम मचाओ मौज मनाओ,आप सभी को दिवाली की बधाई.\n" +
                "शुभ दीवाली”");
        messageTypeList.add(message);

        message = new MessageType("“अपने मन के मन्दिर मे उजाले भर के देखें हम; सजा कर दीप खुशियों के रौशनी कर के देखें हम; चलो अब मिलजुल कर साथ सब मुस्कुराये हम; भुला कर शिकवे इस मन के दीवाली ख़ुशी से मनायें हम।\n" +
                "दिवाली की शुभ कामनायें!”");
        messageTypeList.add(message);

        message = new MessageType("“पल पल सुनहरे फूल खिले; कभी ना हो, कांटो का सामना; जिंदगी आपकी खुशियों से भरी रहे; यही है, इस दीपावली, पर हमारी दिल से ‘शुभकामना’!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);

        message = new MessageType("“दिवाली पर्व है, खुशियों का, उजालो का, लक्ष्मी का! इस दीपावली पर आपकी जिंदगी खुशियों से भरी हो; दुनिया उजालो से रोशन हो; और घर पर माँ लक्ष्मी का आगमन हो! शुभ दीपावली!”");
        messageTypeList.add(message);

        message = new MessageType("“दिवाली पर्व है खुशियों का, उजालों का, लक्ष्मी का, इस दिवाली आपकी जिंदगी खुशियों से भरी हो, दुनियां उजालों से रोशन हो, घर पर माँ लक्ष्मी का आगमन हो!\n" +
                "शुभ दीपावली !”");
        messageTypeList.add(message);

        message = new MessageType("“दीप जलते जगमगाते रहें; हम आपको आप हमें याद आते रहें; जब तक जिंदगी है, दुआ है हमारी; आप चाँद की तरह जगमगाते रहें!\n" +
                "शुभ दीपावली!”\n");
        messageTypeList.add(message);

        message = new MessageType("मैं प्रार्थना करती हूँ भगवान् से कि वो आपको शान्ति, शक्ति, सम्पत्ति, स्वरूप, सयम, सादगी, सफ़लता, समृद्धि, संस्कार, स्वास्थय, सम्मान, सरस्वती और स्नेह दे…. शुभ दीपावली");
        messageTypeList.add(message);

        message = new MessageType("“रोशन हो जाए घर आपका, सज उठे आपकी पूजा की थाली , दिल में यही उमंग है मेरे, खुशियाँ लाए आपके लिए ये दीवाली”");
        messageTypeList.add(message);

        message = new MessageType("“धनतेरस’ में आप धनवान हो; ‘रूपचौदास’ में आप रूपवान हो; ‘दिवाली’ में आपका जीवन जगमगाता हो; ‘भाईदूज’ पर रिश्तों में मिठास हो…\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);

        message = new MessageType("झिलमिलते दीपों की आभा से प्रकाशित ये दीवाली आपके घर आँगन में धन धान्य सुख सम्रिधि और ईश्वर का अन्नत आशीर्वाद लेकर आए..!");
        messageTypeList.add(message);

        message = new MessageType("“जब तक नकली मावा, नकली घी की मिठाई वाली खबरें न्यूज़ में नहीं आ जाती, तब तक लगता ही नही है की दिवाली आने वाली है।\n" +
                "काश, आपको प्रदुषण-रहत और मिलावट-रहत दिवाली की बक्शीश हो!”");
        messageTypeList.add(message);

        message = new MessageType("“हरियाणा और महाराष्ट्र के लोग अगर सस्ते पठाखे ख़रीदना चाहते हों तो… Congress, NCP, INLD और Shiv Sena के आफिस से सम्पर्क करें।\n" +
                "शुभ दीपावली।”");
        messageTypeList.add(message);

        message = new MessageType("“दीप जलते रहे मन से मन मिलते रहे गिले सिकवे सारे मन से निकलते रहे\n" +
                "सारे विश्व मे सुख-शांति की प्रभात ले आये ये दीपो का त्योहार खुशी की सोंगात ले आये.”");
        messageTypeList.add(message);

        message = new MessageType("“दिनों दिन बढ़ता जाये आपका कारोबार; परिवार में बना रहे स्नेह और प्यार; होती रहे सदा अपार धन की बौछार; ऐसा हो आपका दिवाली का त्योंहार।\n" +
                "हैप्पी दिवाली!”");
        messageTypeList.add(message);

        message = new MessageType("“दीवाली पूर्व सूचना !!!\n" +
                "कृपया… आपके ‘GF/BF’ के द्वारा दिएगए और आपके द्वारा छुपाये गए ‘फोटो, प्रेमपत्र, उपहार’ या अन्य कोई ‘चीज’याद से निकल लें, अन्यथा..घर की साफ सफाई करते समय यह आपके’ माँ, पिता या पत्नी’ को मिल सकता है और इस अवस्था में आपके घर में दिवाली से पहले ही’पटाखे’फूट सकते हैँ..!!\n" +
                "सूचना प्रभाग द्वारा जनहित मेँ जारी”");
        messageTypeList.add(message);

        message = new MessageType("“पप्पू: मम्मी, इस बार हम सारे पटाके यहीं से लेंगे!\n" +
                "जीतो: लेकिन यह तो लड़कियों का हॉस्टल है!\n" +
                "पप्पू: पापा ही कहते थे कि सारी फुलझड़ियाँ यहीं पर दिखती हैं!\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);

        message = new MessageType("“एक दुआ मांगते है हम अपने भगवान से चाहते है आपकी ख़ुशी पुरे ईमान से, सब हसरतें पूरी हो आपकी , और आप मुस्कराये दिल -ओ -जान से !!”");
        messageTypeList.add(message);

        message = new MessageType("“गुल ने गुलशन से गुलफाम भेजा है; सितारों ने गगन से सलाम भेजा है; मुबारक हो आपको ये दिवाली; हमने तहे दिल से यह पैगाम भेजा है!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);

        message = new MessageType("“धन लक्ष्मी से भर जाये घर हो वैभव अपार खुशियो के दीपो से सज्जित हो सारा संसार\n" +
                "आंगन आये बिराजे लक्ष्मी करे विश्व सत्कार मन आंगन मे भर दे उजाला दीपो का त्योहार.!”");
        messageTypeList.add(message);

        message = new MessageType("“दिवाली तुम भी मनाते हो; दिवाली हम भी मनाते हैं; बस फर्क सिर्फ इतना है कि हम दियें जलाते हैं;\n" +
                "और तुम दिल जलाते हो!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);

        message = new MessageType("“है रोशनी का यह त्योहार; लाये हर चेहरे पर मुस्कान; सुख और समृधि की बहार; समेट लो सारी खुशियाँ, अपनों का साथ और प्यार!\n" +
                "इस पावन अवसर पर, आप सब को दिवाली का प्यार!”");
        messageTypeList.add(message);

        message = new MessageType("Safalta Kadam Chumti rahe, Khushi Aaspas ghumti rahe, Yash Itna faile ki KASTURI Sharma Jaye, Laxmi ki kripa itni ho ki BALAJI bhi dekhte rah jaye.");
        messageTypeList.add(message);

        message = new MessageType("Aayi hai diwali dekho, Sang layi khushiya dekho.. Yehan wahan jahan dekho Aaj deep jagmagate dekho *Happy Diwali*");
        messageTypeList.add(message);


        message = new MessageType("Deepak ka prakash har pal aapke jivan me ek nayi roshni de, Bas yehi shubhkamna hai hamari aapke liye diwali ke is paawan avsar par, !! Happy diwali !!\n");
        messageTypeList.add(message);

        message = new MessageType("Tu patakha hai kisi aur kaa.. tujhe phodta koi aur hai ..");
        messageTypeList.add(message);


        message = new MessageType("Raat Thi Kaali, Life Thi Khaali, Phir Sab Kutch Badla..Jo Ayee Diwali!!");
        messageTypeList.add(message);

        message = new MessageType("“आज ढाई – तीन बजे के बाद उन लोगो को टेंशन शुरू हो जायेगा जिन्होंने कहा था की…\n" +
                "Payment दिवाली बाद दे दूंगा!”\n");
        messageTypeList.add(message);


        message = new MessageType("“दीपक की रौशनी, पटाखों की आवाज,सूरज की किरणे,खुशियों की बोछार, चन्दन की खुशबु, अपनों का प्यार,मुबारक हो आप को दीवाली का त्यौहार.. “");
        messageTypeList.add(message);

        message = new MessageType("“अगर आप मुझे मैसेज नहीं करोगे तो कुछ दिन बाद आपके आस पास बम फटेगा!\n" +
                "डरो मत क्योंकि कुछ दिन बाद दिवाली है!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);


        message = new MessageType("“दीपक का पर्काश हर पल आपके जीवन मैं एक नयी रौशनी दे, बस यही शुभकामना है हमारी आपके लिए दीवाली के इस पवन अवसर पर ..\n" +
                "** शुभ दीवाली **”");
        messageTypeList.add(message);

        message = new MessageType("पटाखों फुलझड़ियों के साथ; मस्ती से भरी हो दिवाली की रात; प्यार भरे हो दिन ये सारे; खुशियां रहें सदा साथ तुम्हारे।\n" +
                "हैप्पी दिवाली!”\n");
        messageTypeList.add(message);


        message = new MessageType("“दीये से दीये को जला कर दीप माला बनाओ ,अपने घर आंगन को रौशनी से जगमगाओ, आप और आप के परिवार की दीवाली शुभ और मंगलमय हो ,\n" +
                "** शुभ दीवाली ** “");
        messageTypeList.add(message);

        message = new MessageType("“फूलों की शुरुआत कली से होती है; जिंदगी की शुरुआत प्यार से होती है; प्यार की शुरुआत अपनों से होती है; और अपनों की शुरुआत आपसे होती है!\n" +
                "मंगल्म्यें दिवाली!”");
        messageTypeList.add(message);


        message = new MessageType("“लक्ष्मी आएगी इतनी की सब जगह नाम होगा,दिन रात व्यपार बड़े इतना अधिक काम होगा, घर परिवार समाज मैं बनोगे सरताज,ये ही कामना है हमारी आप के लिए ,\n" +
                "*दीवाली की ढेरो शुभकामनाएं* “");
        messageTypeList.add(message);

        message = new MessageType("“मेरी दुआ है कि यह पवित्र त्योहार आपके जीवन में उत्साह, खुशियाँ, शांति और प्यार से सदा के लिए आपके जीवन को भर दे! यह उत्साह वाला पल आपके जीवन को खुशियों से भर दे और दीपों की रोशनी सा आपका जीवन चमक उठे और आपकी सभी तमन्नाएं और सारे सपने पूरे हों!\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);


        message = new MessageType("“खूब मीठे मीठे पकवान खाएं,सेहत मैं चार चाँद लगायें, लोग तो सिर्फ चाँद तक गए हैं ,आप उस से भी ऊपर जाएँ ,\n" +
                "दीवाली की शुभकामनायें “");
        messageTypeList.add(message);

        message = new MessageType("“आपकी ज़िंदगी में, मिठास हो Cadbury जैसे; रौनक हो Asian Paints जैसे; महक हो Axe जैसे; ताज़गी हो Colgate जैसे; और टेंशन मुक्त रहे Huggies जैसे;\n" +
                "शुभ दिवाली!”");
        messageTypeList.add(message);


        message = new MessageType("खुशियो के दीपो से सज्जित हो सारा संसार, आंगन आये बिराजे लक्ष्मी करे विश्व सत्कार, मन आंगन मे भर दे उजाला दीपो का त्योहार… दीपो का त्योहार ह्रदय मे उम्मीदे भर जाये, सुरभित हो जाये जीवन हर इक मन हरसाये, खुशियो की अनमोल घडी हर आंगन मे आये, ये दीवाली ताज ऐसी सारा संसार मनाये…");
        messageTypeList.add(message);

        message = new MessageType("“पटाखे जलाना वातावरण का नुकसान; मिठाई खाना सेहत का नुकसान; तोहफे देना पैसे का नुकसान; इसीलिए सिर्फ दिल से शुभ कामनाएं भेजी हैं; स्वीकार करें मेहरबान!\n" +
                "दीपावली मुबारक! “\n");
        messageTypeList.add(message);


        message = new MessageType("दीपक का पर्काश हर पल आपके जीवन मैं एक नयी रौशनी दे, बस यही शुभकामना है हमारी आपके लिए दीवाली के इस पवन अवसर पर..\n" +
                "** शुभ दीवाली **\n");
        messageTypeList.add(message);

        message = new MessageType("“जीतिए सैमसंग गैलेक्सी S4,माइक्रोवेव फ्रिज,एयर कंडीशन,और एक बम्पर इनाम एक आलीशान घर दुबई में। जितने के लिए कोई भी नुकीली चीज़ से यहाँ स्क्रेच करे और जीते हजारो के इनाम।\n" +
                "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n" +
                "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n" +
                "आप हो सकते है लकी विजेता !”\n");
        messageTypeList.add(message);


        message = new MessageType("“पल पल सुनहरे फूल खिले; कभी ना हो, कांटो का सामना; जिंदगी आपकी खुशियों से भरी रहे; यही है, इस दीपावली, पर हमारी दिल से ‘शुभकामना’");
        messageTypeList.add(message);

        message = new MessageType("“फूलों की शुरुआत कली से होती है; जिंदगी की शुरुआत प्यार से होती है; प्यार की शुरुआत अपनों से होती है; और अपनों की शुरुआत आपसे होती है!\n" +
                "मंगलमय दिवाली! “");
        messageTypeList.add(message);


        message = new MessageType("“रोशनी और ख़ुशी का पर्व है दिवाली का त्योहार; परंतु फ़ुलझाड़ियाँ करें आँखों को अंधा; और हाथों को जला सकते हैं अनार; वातावरण से प्रदूषित होता है संसार; कृपा सावधानी बरतें मेरे यार; इससे होगा जरूर सबका उधार; आप सबको सुखी और सुरक्षित; दीपावली का यह पावन त्योहार!”");
        messageTypeList.add(message);

        message = new MessageType("“एक दुआ मांगते हैं, हम अपने भगवान से; चाहते हैं आपकी ख़ुशी पूरे ईमान से; सब हसरतें पूरी हो आपकी; और आप मुस्कराएं दिलों जान से!\n" +
                "शुभ दीपावली!”\n");
        messageTypeList.add(message);


        message = new MessageType("“होली रंगे सबको रंग में, दिलों की कड़वाहट मिटाए ईद! पर इस रोशनी के त्योहार में मैं तो बस चाहूँ, तेरी दीद!\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);

        message = new MessageType("“पर्व है पुरुषार्थ का, दीप के दिव्यार्थ का; देहरी पर दीप जगमग एक जलता रहे; अंधकार से निरंतर युद्ध यह चलता रहे; हारेगी हर बार अंधियारे की घोर-कालिमा; जीतेगी जगमग उजियारे की स्वर्ण-लालिमा; झिलमिल रोशनी में निवेदित दिवाली की शुभकामना।”");
        messageTypeList.add(message);


        message = new MessageType("दीये से दीये को जला कर दीप माला बनाओ, अपने घर आंगन को रौशनी से जगमगाओ, आप और आप के परिवार की दीवाली शुभ और मंगलमय हो,\n" +
                "** शुभ दीवाली **");
        messageTypeList.add(message);

        message = new MessageType("“स्वर्ग लोक से माता लक्ष्मी; ब्रम्ह लोक से, ब्रम्हा जी; कैलाश से, शिव जी; और पृथवी से मेरी तरफ से;\n" +
                "दीपावली का हार्दिक अभिनंदन! “\n");
        messageTypeList.add(message);


        message = new MessageType("“तू हम से क्या बराबरी करेगी बावली, हम तो हर दिन मनाते है दीपावली ।””");
        messageTypeList.add(message);

        message = new MessageType("“रौशन हो दीपक और सूरज जगमगाए; लिए साथ सीता मईया को राम जी हैं आए; हर शहर यूँ लगे मानो अयोध्या हैं हम आए; आओ हर घर में, सबके मन में खुशियों के दीप जलाएं।\n" +
                "दिवाली की शुभ कामनायें!”");
        messageTypeList.add(message);


        message = new MessageType("“पडोसी आंटी इतने कॉन्फिडेंस से मुझसे बच्चे के राकेट चलाने के लिए दारू की खाली बोतल मांग रही जैसे… रोज मैं दारु पीकर उनकी चौखटपर उल्टिया करता हूँ.”");
        messageTypeList.add(message);

        message = new MessageType("“पटाखों की आवाज से गूंज रहा संसार; दीपक की रोशनी अपनों का प्यार; मुबारक हो आपको दीपावली का त्योहार।\n" +
                "हैप्पी दिवाली!”");
        messageTypeList.add(message);


        message = new MessageType("“दीपावली का यह पावन त्यौहार , जीवन में लाये खुशियाँ अपार , लक्ष्मी जी विराजे आपके द्वार , शुभकामनायें हमारी करें स्वीकार !!\n" +
                "सपरिवार दिवाली की हार्दिक शुभकामनायें ।”");
        messageTypeList.add(message);

        message = new MessageType("“दीपावली का त्यौहार आ रहा है! मैं अपना मन भगवान् में सिर्फ पूजा, अर्चना, आरती, श्रद्धा, उपासना और प्रार्थना के साथ लगाना चाहता हूं! आपके पड़ोस में कोई रहती है, तो बताओ!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);


        message = new MessageType("“दिवाली के इस शुभ अवसर पर यह दुआ है कि आपके घर माँ लक्ष्मी का वास हो; धन की बेतहाशा बरसात हो; संकटों का पूरा नाश हो; हर दिल पर आपका राज हो; और कामयाबी का सिर पर ताज हो।\n" +
                "दिवाली की शुभ कामनायें!”");
        messageTypeList.add(message);

        message = new MessageType("“मुस्कुराते हँसते दीप तुम जलाना; जीवन में नई खुशियों को लाना; दुःख दर्द अपने भूल कर; सबको गले लगाना।\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);


        message = new MessageType("“कुमकुम भरे क़दमों से; आये लक्ष्मी आपके द्वार; सुख संपति मिले आपको अपरंपार; खुशियों से भरा रहे सारा साल; आपका हर सपना हो साकार!\n" +
                "शुभ दीपावली!”\n");
        messageTypeList.add(message);

        message = new MessageType("“दीपावली का ये पावन त्योहार; जीवन में लाये खुशियाँ अपार; लक्ष्मी जी, विराजे आपके द्वार; हमारी शुभकामनाएं करें स्वीकार!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);


        message = new MessageType("“तू ही मेरी फूलझड़ी, तू ही मेरा बम; तू ही मेरा रॉकेट, तू ही मेरा अनार! फिर आ गया है उत्सव रोशनी का; तुम्हें मुबारक हो दीपावली का त्योहार!\n" +
                "दीपावली मुबारक!”");
        messageTypeList.add(message);

        message = new MessageType("“गुल ने गुलशन से गुलफ़ाम भेजा है; सितारों ने गगन से सलाम भेजा है; मुबारक हो आप को यह दीपावली; हमने तहे दिल से यह पैगाम भेजा है!\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);


        message = new MessageType("“हर ख़ुशी, ख़ुशी मांगे आपसे; हर जिंदगी, जिंदगी मांगे आपसे; इतना उजाला हो आपके जीवन में कि;\n" +
                "दियें भी रोशनी मांगे आपसे।\n" +
                "हैप्पी दिवाली!”");
        messageTypeList.add(message);

        message = new MessageType("“पल पल से बनता है, ‘एहसास’; एहसास से बढ़ता है, ‘विश्वास’; विश्वास से ही बनते हैं, ‘रिश्ते’; और रिश्तों से ही बनता है, कोई ‘ख़ास’; मुबारक हो आपको ये दिवाली ‘झकास’!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);


        message = new MessageType("“लक्ष्मी आएगी इतनी कि सब जगह नाम होगा; दिन रात व्यापार बढ़ेगा, इतना अधिक काम होगा; घर परिवार समाज में बनोगे सरताज; यही कामना है, हमारी आपके लिए;\n" +
                "दिवाली की ढेरो शुभ कामनाएं!”");
        messageTypeList.add(message);

        message = new MessageType("“आपके लिए आए ऐसे यह त्योहार; जिसमे मिले आपको कैटरीना का प्यार; एश्वर्या की ममता और और अनुश्का का दुलार; अमीषा, करीना और दीपिका की लगन से; मुबारक हो आपको यह पावन त्योहार!\n" +
                "शुभ दीपावली!”");
        messageTypeList.add(message);


        message = new MessageType("“धन लक्ष्मी से भर जाये घर, हो वैभव अपार; खुशियों के दीपों से सज्जित हो सारा संसार; आंगन आये बिराजे लक्ष्मी करे विश्व सत्कार; मन आंगन मे भर दे उजाला ये दीपों का त्योहार।\n" +
                "दिवाली की शुभ कामनायें!”");
        messageTypeList.add(message);

        message = new MessageType("“दीपों का उजाला; पटाखों का रंग; धूप की ख़ुशी; प्यार भरी उमंग; मिठाई का स्वाद; अपनों का प्यार; मुबारक हो आपको; दीपावली का त्यौहार।\n" +
                "हैप्पी दिवाली!”\n");
        messageTypeList.add(message);


        message = new MessageType("“दीपावली के इस पावन पर्व पर हम उस परम पूज्य परमात्मा से प्रार्थना करते हैं कि; माता लक्ष्मी आपके ऊपर अपार कृपा करें और; आपका जीवन इस रोशन पर्व के समान जगमाता रहे।\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);

        message = new MessageType("“कुमकुम भरे क़दमों से आई लक्ष्मी जी आपके द्वार; सुख समपत्ति मिले आपको अपरमपार; इस दीपावली पर माता लक्ष्मी जी; आपकी सभी तमन्नाहें करें स्वीकार!\n" +
                "शुभ दीपावली! “");
        messageTypeList.add(message);

        /*message = new MessageType("");
        messageTypeList.add(message);*/




        mAdapter.notifyDataSetChanged();
    }
}
