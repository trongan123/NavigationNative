import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = MethodChannel('com.navigation.flutter/screen.three');

class ScreenThreePage extends StatefulWidget {
  const ScreenThreePage({super.key, required this.title});

  final String title;

  @override
  State<ScreenThreePage> createState() => _ScreenThreePageState();
}

class _ScreenThreePageState extends State<ScreenThreePage> {
  void sendEventToNative(String action) async {
    try {
      await platform.invokeMethod('onFlutterEvent', action);
    } on PlatformException catch (e) {
      print("Failed to send event: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text('Screen Three'),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Present");
              },
              child: const Text("Present"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Back");
              },
              child: const Text("Back"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("ClearStack");
              },
              child: const Text("Back and Clear All Stack"),
            ),
            const SizedBox(height: 10),
          ],
        ),
      ),
    );
  }
}
