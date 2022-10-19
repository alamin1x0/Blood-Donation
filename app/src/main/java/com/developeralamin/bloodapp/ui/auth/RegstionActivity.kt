package com.developeralamin.bloodapp.ui.auth


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.developeralamin.bloodapp.model.UserModel
import com.developeralamin.bloodapp.databinding.ActivityRegstionBinding
import com.developeralamin.bloodapp.ui.MainActivity
import com.developeralamin.bloodapp.utils.Config
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegstionActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegstionBinding

    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    lateinit var blood: String
    lateinit var division: String
    lateinit var districts: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegstionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.userLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


        val item =
            arrayOf("Select Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-")

        binding.spinnerBloodGroup.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                item
            )
        )

        binding.spinnerBloodGroup.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                blood = binding.spinnerBloodGroup.getSelectedItem().toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


        val items =
            arrayOf(
                "Select Division",
                "Dhaka",
                "Rajshahi",
                "Barisal",
                "Chittagong",
                "Rangpur",
                "Sylhet",
                "Khulna",
                "Mymensingh"
            )

        binding.spinnerDivsion.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                items
            )
        )

        binding.spinnerDivsion.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                division = binding.spinnerDivsion.getSelectedItem().toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })


        val item3 =
            arrayOf(
                "Select District",
                "Dhaka",
                "Faridpur",
                "Gazipur",
                "Gopalganj",
                "Jamalpur",
                "Kishoreganj",
                "Madaripur",
                "Manikganj",
                "Munshiganj",
                "Mymensingh",
                "Narayanganj",
                "Narsingdi",
                "Netrokona",
                "Rajbari",
                "Shariatpur",
                "Sherpur",
                "Tangail",
                "Bogra",
                "Joypurhat",
                "Naogaon",
                "Natore",
                "Nawabganj",
                "Pabna",
                "Rajshahi",
                "Sirajgonj",
                "Dinajpur",
                "Gaibandha",
                "Kurigram",
                "Lalmonirhat",
                "Nilphamari",
                "Panchagarh",
                "Rangpur",
                "Thakurgaon",
                "Barguna",
                "Barisal",
                "Bhola",
                "Jhalokati",
                "Patuakhali",
                "Pirojpur",
                "Bandarban",
                "Brahmanbaria",
                "Chandpur",
                "Chittagong",
                "Comilla",
                "Cox''s Bazar",
                "Feni",
                "Khagrachari",
                "Lakshmipur",
                "Noakhali",
                "Rangamati",
                "Habiganj",
                "Maulvibazar",
                "Sunamganj",
                "Sylhet",
                "Bagerhat",
                "Chuadanga",
                "Jessore",
                "Jhenaidah",
                "Khulna",
                "Kushtia",
                "Magura",
                "Meherpur",
                "Narail",
                "Satkhira"
            )

        binding.spinnerDistricts.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                item3
            )
        )

        binding.spinnerDistricts.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                districts = binding.spinnerDistricts.getSelectedItem().toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        binding.signIn.setOnClickListener {

            val name = binding.userName.text.toString()
            val phone = binding.userPhone.text.toString()
            val email = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()

            if (name.isEmpty()) {
                binding.userName.setError("Please Enter your Name")
                binding.userName.requestFocus()
            } else if (phone.isEmpty()) {
                binding.userPhone.setError("Please Enter your Phone")
                binding.userPhone.requestFocus()
            } else if (email.isEmpty()) {
                binding.userEmail.setError("Please Enter your Email")
                binding.userEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.userPassword.setError("Please Enter your Password")
                binding.userPassword.requestFocus()
            } else if (blood.equals("Select Blood Group")) {
                Toast.makeText(this, "Please Provide Blood Group", Toast.LENGTH_SHORT).show()
            } else if (division.equals("Select Division")) {
                Toast.makeText(this, "Please Provide Division", Toast.LENGTH_SHORT).show()
            } else if (districts.equals("Select District")) {
                Toast.makeText(this, "Please Provide District", Toast.LENGTH_SHORT).show()
            } else {

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Config.showDialog(this)
                        val currentUserId = auth.currentUser!!.uid
                        val data = UserModel(
                            currentUserId,
                            name,
                            phone,
                            blood,
                            division,
                            districts,
                            email,
                            password
                        )

                        db.collection("users").document(currentUserId).set(data)
                            .addOnCompleteListener {
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Config.hideDialog()
                        Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }
}