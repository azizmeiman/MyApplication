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
        Policy.setText("الطرف الاول: عارض العمال\n" +
                "الطرف الثاني: باحث عن عامل\n" +
                "1.     يجب التعامل مع العمال وفقا لما نصت عليه الشريعة الاسلامية و لوائح وزارة العمل المتخصصة بالتعامل مع العمال.              \n" +
                "2.     لا يحق للطرف الثاني تغيير أو تأجيل أيام أو فترة التوظيف من دون موافقة الطرف الاول.\n" +
                "3.     في حال عدم مقدرة الطرف الأول لأي سبب كان على تنفيذ الزيارة يتم تعويض الطرف الثاني بزيارة بديلة و يتم تحديد الموعد حسب امكانية الطرف الأول ويتم تعويض الطرف الثاني بخصم قدرة 25% عن كل تأخير.\n" +
                "4.     يلتزم الطرف الثاني بتشغيل العامل بمدة اقصاها 8 ساعات باليوم.\n" +
                "5.      تخضع قيمة الخدمة لضريبة القيمة المضافة (VAT).\n" +
                "6.     في حال عدم استقبال الطرف الثاني للعامل عند وصوله للمزرعة خلال نصف ساعه من وقت الاتصال الذي يسبق الوصول تعتبر الزيارة نافذة ولا يتم تعويضه عنها.  \n" +
                "7.     تقع المسؤولية على الحفاظ على الممتلكات الخاصة بالطرف الثانيه عليه وحده، ويجب عليه ان يكون قريب من العامل ولا يحق له مطالبة الطرف الاول بأي مطالبات وتخلى مسؤوليته في حال فقدان اي اغراض شخصية وتكون المسؤولية الوحيدة على عاتق العامل بما نصت عليه قوانين المملكة العربية السعودية بخصوص الجرائم الجنائية.   \n" +
                "8     يؤدي العامل الوظائف المتخصصه به كمثال(مزارع: يقوم بما يخص الزراعه)(سائق حراثة: يقوم بأعمال الحراثة فقط) وهذا يسري على جميع العمال ولايمكن اجبارهم على القيام بأعمال اضافية من دون موافقتهم.\n" +
                "9     الغاء العقد:         \n" +
                "·    *     يحق للطرف الثاني الغاء الخدمة قبل أو بعد البدء في تقديم الخدمة ويتم احتساب قيمة الزيارات النافذة بالاضافة الى رسوم الغاء ومقدارها(200)ريال سعودي ويتم استرجاع القيمة المتبقية بعد الخصم من خلال حوالة بنكية على حساب الطرف الثاني.  \n" +
                "·    *     في حالة الاخلال بأي من شروط وبنود هذا العقد او الاعتداء على العامل يحق للطرف الاول الغاء العقد دون الرجوع الى الطرف الثاني ولا يحق للطرف الثاني المطالبة بأي مبالغ مالية مع احتفاظ الطرف الاول بحق مقاضاته لدى الجهات المختصة في المملكة العربية السعودية. ");

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
