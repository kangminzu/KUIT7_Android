package com.example.kuit7

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsScreen(modifier: Modifier = Modifier) {
    val categories = listOf(
        "All", "Sports", "Politics", "Business", "Health", "Travel", "Science",
        "Fashion", "Technology", "Food", "Entertainment", "Finance", "Education", "Weather"
    )

    val newsList = listOf(
        News("Europe", "Ukraine's President Zelensky to BBC: Blood money being paid...", "BBC News", "14m ago", R.drawable.week2_newsimages1, R.drawable.ic_launcher_foreground),
        News("Travel", "Her train broke down. Her phone died. And then she met her..", "CNN", "1h ago", R.drawable.week2_newsimages2, R.drawable.ic_launcher_foreground),
        News("Europe", "Russian warship: Moskva sinks in Black Sea", "BBC News", "4h ago", R.drawable.week2_newsimages3, R.drawable.ic_launcher_foreground),
        News("Money", "Wind power produced more electricity than coal and nucle...", "USA Today", "4h ago", R.drawable.week2_newsimages4, R.drawable.ic_launcher_foreground),
        News("Life", "We keep rising to new challenges: For churches hit by", "USA Today", "4h ago", R.drawable.week2_newsimages5, R.drawable.ic_launcher_foreground),
        News("Politics", "New environmental policy debated in the Senate", "Reuters", "2h ago", R.drawable.week2_newsimages1, R.drawable.ic_launcher_foreground),
        News("Sports", "Unexpected victory in the final match of the season", "ESPN", "3h ago", R.drawable.week2_newsimages2, R.drawable.ic_launcher_foreground),
        News("Health", "New study reveals benefits of daily meditation", "Healthline", "5h ago", R.drawable.week2_newsimages3, R.drawable.ic_launcher_foreground),
        News("Business", "Stock market reaches all-time high amidst tech boom", "Forbes", "6h ago", R.drawable.week2_newsimages4, R.drawable.ic_launcher_foreground),
        News("Science", "First clear images of distant galaxy captured by telescope", "NASA", "8h ago", R.drawable.week2_newsimages5, R.drawable.ic_launcher_foreground),
        News("Travel", "Top 10 hidden gems to visit in Europe this summer", "Lonely Planet", "10h ago", R.drawable.week2_newsimages1, R.drawable.ic_launcher_foreground),
        News("Fashion", "Milan Fashion Week kicks off with bold new designs", "Vogue", "12h ago", R.drawable.week2_newsimages2, R.drawable.ic_launcher_foreground),
        News("Technology", "New AI model breakthrough in natural language processing", "TechCrunch", "1d ago", R.drawable.week2_newsimages3, R.drawable.ic_launcher_foreground)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        // 앱 아이콘 영역 (크기를 dp로 고정하여 적절하게 조절)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.newsappicon),
                contentDescription = "App Logo",
                modifier = Modifier.width(120.dp), // 너비를 120dp로 고정
                contentScale = ContentScale.FillWidth
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Latest", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "See all", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(categories) { category ->
                Text(
                    text = category, 
                    fontSize = 16.sp, 
                    fontWeight = if(category == "All") FontWeight.Bold else FontWeight.Normal,
                    color = if(category == "All") Color.Black else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // LazyColumn으로 메모리 효율적인 목록 렌더링
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(newsList) { news ->
                NewsItem(news)
            }
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = news.image),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.height(96.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = news.category, fontSize = 13.sp, color = Color.Gray)
                Text(
                    text = news.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = news.sourceIcon),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = news.source, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = news.time, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsScreenPreview() {
    NewsScreen()
}
