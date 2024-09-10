package com.example.androidinterviewapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androidinterviewapplication.ui.theme.AndroidInterviewApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidInterviewApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // TODO center column both horizontally and vertically
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var stringToValidate by remember { mutableStateOf("") }
                        var isValid by remember { mutableStateOf(false) }

                        // TODO change this to an outlined text field
                        // TODO add label to text field and extract string to strings.xml
                        // TODO add padding of 16dp
                        OutlinedTextField(
                            value = stringToValidate,
                            onValueChange = { stringToValidate = it },
                            modifier = Modifier.padding(16.dp),
                            label = { Text(text = getString(R.string.string_to_validate))}
                        )

                        // TODO change color of button to Colors.Blue
                        // TODO add padding of 16dp
                        Button(
                            onClick = { isValid = bracketValidator(stringToValidate) },
                            modifier = Modifier
                                .padding(16.dp)
                                .background(Color.Blue)
                        ) {
                            // TODO extract string to strings.xml
                            Text(text = getString(R.string.validate))
                        }

                        // TODO make the text italic
                        // TODO make the text bold
                        // TODO add padding of 16dp
                        Text(isValid.toString(),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

// TODO add logic to validate brackets
fun bracketValidator(string: String): Boolean {
    val charList = mutableListOf<Char>()
    val matchingBrackets = mapOf(')' to '(', '}' to '{', ']' to '[')

    for (char in string) {
        when (char) {
            '(', '{', '[' -> charList.add(char) // Add opening brackets to the list
            ')', '}', ']' -> {
                // Empty or end of the list should return false
                if (charList.isEmpty() || charList.removeAt(charList.size - 1) != matchingBrackets[char]) {
                    return false
                }
            }
        }
    }

    // Should return true if all brackets are matched
    return charList.isEmpty()
}
