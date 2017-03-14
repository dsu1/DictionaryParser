## Dictionary Parsing Challenge

Imagine you have recently written a new language and collected all its words into a Dictionary. Similar to the English language, words can be categorized into nouns, verbs and articles. Below is the Dictionary:

Nouns: "abcd", "c", "def", "h", "ij", "cde"

Verbs: "bc", "fg", "g", "hij", "bcd"

Articles: "a", "ac", "e"

However, the rules for creating a sentence in this Language are very different. A valid sentence in this language should:
- have all its words present in the Dictionary.
- have a verb.
- have a noun or have at least two articles.

Your task is to write a sentence composer which will take a string as an input and return all possible valid sentences. This composer keeps the characters of the string in the same order, while inserting at most one space between characters as necessary, to create valid words and a valid sentence.

For your convenience, we have provided some sample inputs and outputs.
Input = "abcdefg", should return the following list:

[

"a bc def g",

"a bcd e fg",

"abcd e fg"

]

Input = "abcc", should return the following list:
["a bc c"]

Input = "abcd", should return the following list:
[]

Note: Make sure to list all (if any) assumptions you make. 

#### Assumptions
* The order of valid output doesn't matter

### Abstract
This program begins by accepting input then attempts to match every word in the dictionary against the input from start to finish. It begins by checking if the first character of the input matches the first character of every word, then calls a word completion function that checks if every character of the word matches the input. All matching words are added to a list which will keep track of:
* Each sentence as they are being composed
* The index position of the input given previously matched words
* The number of verbs, nouns and articles that each valid word addition sums to per sentence

So long as this list isn't empty, the program will continue to match and append new words to an incomplete sentence until the sentence is completed or shows to be invalid. All completed sentences are added to the 'complete' list and removed from the 'processing' list which are then validated. This validation check views the count of nouns, verbs, and articles and passes sentences that meet the requirements on the the 'finished' list. The finished list outputs its contents to the console.

### Step-by-Step
* A scanner object is created to accept user input and run the sentence composer
* An initial round of iteration matches the first character of input to the first character of every word in the dictionary
* When the first characters match, the scanWord function processes the matching verb, noun, or article.
* All matching words are added to a Processing string array list along with the length of the word as an offset and the type of word it was. A colon is used as a delimiter. 
* The format of each processing sentence is as follows: [sentence:offset:number of nouns:number of verbs:number of articles]
* If nothing was added to the Processing string array list, then no initial word matches were made and the program outputs square brackets to indicate no valid sentence could be made with the input
* In the case that the first iteration did populate the Processing list with valid first words, a while loop begins to iterate over the first entry in the Processing list until it is emptied. So long as there are entries in the Processing list, this loop will continue.
* In this while loop, an initial check is made for whether the current Process' offset is equal to the input's length. If the offset of the first entry in the Process list matches the length of the input, then a full sentence has been made and it is added to the 'Completed' list and removed from the Processing list.
* If these numbers don't match, the first entry in the Processing list will match the first characters of each word in the dictionary against the input at the given offset. When a match is found, scanWord is once again called to check if the word matches with the input. If a match is found, it is concatenated to its predecessor string, the offset is adjusted, and the type of the current word is incremented while preserving the amount of other words.
* A validation is performed after Processing is emptied either by discarding invalid attempts or adding unvalidated full sentences to the Complete list.
* The Complete list is iterated over, checking that at least a verb and noun or 2 articles are present in all completed sentences. Each sentence that meets this requirement is added to the Finished list and all others are ignored.
* The program ends by printing out the task's format and the valid sentences in the Finished list based on the constraints given.
