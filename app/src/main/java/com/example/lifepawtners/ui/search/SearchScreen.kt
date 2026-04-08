package com.example.lifepawtners.ui.search


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Surface
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val Coral = Color(0xFFFF6150)
private val SoftBackground = Color(0xFFF7F8FC)
private val LightCard = Color(0xFFFFFFFF)
private val DividerColor = Color(0xFFE6EAF2)
private val MutedText = Color(0xFF6B7280)

@Composable
fun SearchScreen() {
    var searchText by remember { androidx.compose.runtime.mutableStateOf("") }
    var ageValue by remember { mutableFloatStateOf(25f) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SoftBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .navigationBarsPadding()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search pets, breeds, shelters...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search"
                    )
                },
                shape = RoundedCornerShape(18.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LightCard)
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.FilterList,
                                contentDescription = "Filter",
                                tint = Coral
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "FILTER",
                                color = Coral,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "Close",
                                tint = MutedText
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "PET TYPE",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF334155)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PetTypeToggle()

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 14.dp),
                        color = DividerColor
                    )

                    Text(
                        text = "AGE",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF334155)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Age: ${ageValue.toInt()} ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedText
                    )

                    Slider(
                        value = ageValue,
                        onValueChange = { ageValue = it },
                        valueRange = 0f..20f
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        FakeCheckboxRow("< 100")
                        FakeCheckboxRow("100 - 150")
                        FakeCheckboxRow("150 - 300")
                        FakeCheckboxRow("> 300")
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 14.dp),
                        color = DividerColor
                    )

                    FilterRow(title = "Breed")
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 14.dp),
                        color = DividerColor
                    )

                    FilterRow(title = "Age")
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 14.dp),
                        color = DividerColor
                    )

                    FilterRow(title = "Size")

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(22.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Coral,
                            contentColor = Color.White
                        )
                    ) {
                        Text("APPLY", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun FilterRow(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF334155),
            fontWeight = FontWeight.Medium
        )

        TextButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.Remove,
                contentDescription = "Collapse",
                tint = Coral
            )
        }

        TextButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Expand",
                tint = Coral
            )
        }
    }
}

@Composable
fun FakeCheckboxRow(label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = null
        )
        Text(
            text = label,
            color = Color(0xFF334155)
        )
    }
}
@Composable
fun PetTypeToggle() {
    var selected by remember { androidx.compose.runtime.mutableStateOf("Dog") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFEDEFF5),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(4.dp)
    ) {
        ToggleOption(
            text = "Dog",
            selected = selected == "Dog",
            onClick = { selected = "Dog" },
            modifier = Modifier.weight(1f)
        )

        ToggleOption(
            text = "Cat",
            selected = selected == "Cat",
            onClick = { selected = "Cat" },
            modifier = Modifier.weight(1f)
        )
    }
}
@Composable
fun ToggleOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .background(
                color = if (selected) Coral else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        TextButton(onClick = onClick) {
            Text(
                text = text,
                color = if (selected) Color.White else Color(0xFF334155),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}