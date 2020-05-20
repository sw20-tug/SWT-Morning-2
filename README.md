# SWT: Morning 2
 
This is the repository for the team *SWT: Morning 2*

## What it is

A collection of games as an Android application. 
The games include: Hangman, TicTacToe and 'Dont Touch The White Tiles'.

## Getting started
Download Android Studio from: https://developer.android.com/studio.

Start Android Studio and choose 'open existing android project' or 'checkout existing project from 
version control' and wait for the gradle build to finish.

After this you have to setup an emulator or use an existing mobile phone with Developer options enabled.
For more details on this check out [this](https://developer.android.com/studio/run/emulator) for emulators
and [this](https://developer.android.com/studio/run/device) for hardware devices.

## Technologies
- Using Java 1.8+
- Android Target SDK 26
- Android Min SDK 24
- Gradle build tool 5.6.4
- Espresso for UI Testing
- Mockito as Mocking Framework in Unit tests
- JUnit in Unit Tests

## Testing
To execute the unit tests execute the gradle test task via `./gradlew test`
and to execute the UI Tests execute the gradle connectedAndroidTest task via `./gradlew connectedAndroidTest`.

## Dev Environment Setup
### Checkstyle
To use checkstyle locally in android studio, you need to install the plugin `CheckStyle-IDEA`. To do that go to File->Settings->Plugins. After installation you have to configure it that it uses our rules. Go to File->Settings->Other Settings->Checkstyle, there click on the plus to add a new configuration file. The location of our config file is `config/checkstyle/checkstyle.xml`. If you run a check you must make sure that the correct config is selected, do this via the dropdown.


