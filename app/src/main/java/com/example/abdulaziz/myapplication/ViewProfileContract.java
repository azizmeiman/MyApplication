package com.example.abdulaziz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewProfileContract extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_contract);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        final TextView contractID = (TextView) findViewById(R.id.ConractID);
        final TextView EmpN = (TextView) findViewById(R.id.empName);
        final TextView workerN = (TextView) findViewById(R.id.WorkerNamec);
        final TextView StertDate = (TextView) findViewById(R.id.StrarDateC);
        final TextView EndDate = (TextView) findViewById(R.id.EndDatec);
        final TextView TotalPrice = (TextView) findViewById(R.id.TotalPriceC);
        final TextView empid = (TextView) findViewById(R.id.empid);
        final TextView posterid = (TextView) findViewById(R.id.posterid);




        final TextView Conditions = (TextView) findViewById(R.id.textView21);
        final TextView Policy = (TextView) findViewById(R.id.TEXT_STATUS_ID);
        Conditions.setText("الشروط و الأحكام");
        Policy.setText("ار إلى المعلومات الخاصة بفلاي دبي والخدمات التي نقدمها، بما في ذلك جداول الرحلات، والمسارات، وأجور السفر، والنصوص، وتصاميم الصور، والأيقونات، ومقاطع الصوت والفيديو، والتنزيلات الرقمية، وتجميع البيانات (بما في ذلك معلومات العميل والمعلومات و/أو البيانات المتعلقة بالتعيين أو التوظيف أو الموارد البشرية) والشعارات والمعلومات عن حالة رحلات الشركة، وغير ذلك، على أنها معلومات الشركة وذلك تنفيذاً لأغراض الاتفاقية.\n" +
                "\n" +
                "وتمتلك فلاي دبي كافة حقوق الملكية الفكرية في الموقع ومحتواه ومواده، وكافة حقوق الملكية الفكرية في قسم \"معلومات الشركة\" بما في ذلك على سبيل المثال لا الحصر حقوق الطبع والنشر، والعلامات التجارية، وحقوق قاعدة البيانات وكافة وأي من الحقوق الأخرى المتعلقة والواردة في قسم \"معلومات الشركة\".\n" +
                "\n" +
                "يتوفر هذا الموقع لاستخدامك الشخصي فقط وليس للاستخدام التجاري، ولا يجوز لك تعديل أو نسخ أو توزيع أو نقل أو عرض أو أداء أو إعادة إنتاج أو نشر أو ترخيص أو إنشاء أي أعمال ناتجة عن نقل أو بيع أي معلومات أو برمجيات أو منتجات أو خدمات تم الحصول عليها من هذا الموقع.\n" +
                "\n" +
                "ويجوز لك طباعة أو تنزيل نسخة واحدة من أي مقتطفات أو صفحات من موقعنا بهدف الاستخدام الشخصي. ولا يجوز لك تعديل أو تنقيح أي ورقة أو مقاطع رقمية مأخوذة عن هذا الموقع أو استخدام أي صور أو سلسلات فيديو/صوت، أو الرسومات التوضيحية أو الصور أو الشعارات على النحو الذي تظهر به على الموقع بشكل منفصل عن اقترانها بأي نص أو وجودها في أي سياق يختلف عما هو معروض على الموقع. والحصول على أي مواد أو مطبوعات أو نسخ أو تنزيلات على نحو ينتهك هذه الاتفاقية هو أمر غير مصرح به وسيؤدي إلى إنهاء فوري لحقك في استخدام الموقع ويجب عليك إتلاف أو إعادة أي مواد في حوزتك.\n" +
                "\n" +
                "إنك توافق وتتعهد بأنك لن تستخدم الموقع أو أي جزء فيه لأغراض تجارية دون الحصول على ترخيص مسبق منّا بذلك. ويتم منح أي ترخيص من هذا القبيل بناءً على تقدرينا الشخصي، وفي الحالات التي نمنحك فيها مثل هذا الترخيص، سيخضع استخدامك لمحتوى موقعنا لأحكام ذلك الترخيص.\n" +
                "\n" +
                "إن كل ما تتم الإشارة إليه من الاسم التجاري لشركة فلاي دبي ولأي منتج آخر أو أي أسماء تجارية لمؤسسة دبي للطيران في هذه الاتفاقية أو ترتبط بها هي علامات خدمتنا أو العلامات التجارية المسجلة لخدمتنا. ولا يجوز لك، دون الحصول على موافقة مسبقة منّا بذلك، تقليدها أو تنفيذها أو حذفها من أجل تنفيذ أي تصرف يمكن أن ينتهك استحقاقنا وحقوقنا في علامات خدمتنا أو علاماتنا التجارية، واستخدامك للموقع لا يمنحك بأي شكل من الأشكال أي حق و/أو استحقاق في هذه العلامات أو العلامات التجارية الخاصة بخدمتنا.\n" +
                "\n" +
                "إنّ أسماء المنتجات والشركات الأخرى الوارد ذكرها في هذه الاتفاقية هي أسماء قد تمثل علامات تجارية لأصحابها، وعلى الرغم من أننا حصلنا على الحق في استخدام هذه العلامات التجارية لأغراض الموقع، إلا أنّ استخدامك للموقع لا يمنحك بأي شكل من الأشكال أي حقوق أو استحقاق في العلامات التجارية الخاصة بأي طرف ثالث.");


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String email = extras.getString("contractID");

        myRef.child("Contract").addValueEventListener(new ValueEventListener() {
            Contract Contract = new Contract();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    if(child.child("contractID").getValue().toString().equals(email)) {

                        Contract = new Contract(child.getValue(Contract.class));
                        contractID.setText("Contract ID: "+Contract.getContractID());
                        EmpN.setText("Employer:"+Contract.getEmpName());
                        workerN.setText("Worker:"+Contract.getWorkerName());
                        StertDate.setText("Stert Date:"+Contract.getStartDate());
                        EndDate.setText("End Date:"+Contract.getEndDate());
                        TotalPrice.setText("Total Price:"+Contract.getTotalprice());
                        empid.setText("Employer ID:"+Contract.getEmpID());
                        posterid.setText("Poster ID:"+Contract.getPosterID());


                        ImageView image = (ImageView)findViewById(R.id.imageView12);

                            image.setImageResource(R.drawable.contract2);


                        break;


                    }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewProfileContract.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }



        });


    }
}
