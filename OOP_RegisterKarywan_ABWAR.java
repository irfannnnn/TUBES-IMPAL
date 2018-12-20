package com.wiranata.abwarregisterkaryawan;

import ...

public class RegisterKaryawan extends AppCompatActivity {
	private EditText username, password;
	private Button btn_regEmp;
	private ProgressBar loading;
	public static String URL_REGIST= "http://192.168.1.100/abwar_register_karyawan/registerkaryawan";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContent(R.layout.activity_regemp);
		
		loading = findViewById(R.id.loading);
		username = findViewById(R.id.username);
		password = findViewById(R.id.password);
		btn_regEmp = findViewById(R.id.btn_regEmp);
		
		btn_regEmp.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				regist();
			}
		});
	}
	
	private void Regist(){
		loading.setVisibility(View.VISIBLE);
		btn_regEmp.setVisibility(View.GONE);
		
		final String username = this.username.getText().toString().trim();
		final String password = this.password.getText().toString().trim();
		
		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
			new Response.Listener<String>(){
				@Override
				public void onResponse(String response){
					try{
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString( username: "success");
						
						if (success.equals("1")){
							Toast.makeText(context: RegisterKaryawan.this, text: "Register Sukses", Toast.LENGTH_SHORT).show();
						}
					}catch (JSONException e){
						e.printStackTrace();
						Toast.makeText(context: RegisterKaryawan.this, text: "Register Error" + e.toString(),Toast.LENGTH_SHORT).show();
						loading.setVisibility(View.VISIBLE);
						btn_regEmp.setVisibility(View.GONE);
					}
				}
			},
			new Respons,ErrorListener(){
				@Override
				public void onErrorResponse(VolleyError error){
					Toast.makeText(context: RegisterKaryawan.this, text: "Register Error" + e.toString(),Toast.LENGTH_SHORT).show();
					loading.setVisibility(View.VISIBLE);
					btn_regEmp.setVisibility(View.GONE);
				}
			})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFaillureError{
				
				return super.getParams();
			}
		};
		
		RequestQueue requestQueue = Volley.newRequestQueue(context: this);
		RequestQueue.addd(stringRequest);
	}
}