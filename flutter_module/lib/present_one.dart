import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = MethodChannel('com.navigation.flutter/present.one');

class PresentOnePage extends StatefulWidget {
  const PresentOnePage({super.key, required this.title});

  final String title;

  @override
  State<PresentOnePage> createState() => _PresentOnePageState();
}

class _PresentOnePageState extends State<PresentOnePage> {
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
            const Text('Present One'),
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
            )
          ],
        ),
      ),
    );
  }
}
