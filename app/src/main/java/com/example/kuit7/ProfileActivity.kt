package com.example.kuit7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kuit7.ui.theme.KUIT7week1Theme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KUIT7week1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 전체 화면을 채우는 Box를 만들고 그 안에서 가운데 정렬
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            Modifier
                                .width(300.dp)
                                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
                                .padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.minzu),
                                contentDescription = "프로필 이미지",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(15.dp))
                            )

                            Text(text = "이름 : 강민주")
                            Text(text = "학과 : 컴퓨터공학부")
                            Text(text = "학번 : 202312338")
                            Text(text = "생년월일 : 2004/01/16")
                        }
                    }
                }
            }
        }
    }
}
