import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platform = MethodChannel('com.navigation.flutter/search');

class ScreenSearchPage extends StatefulWidget {
  const ScreenSearchPage({super.key, required this.title});

  final String title;

  @override
  State<ScreenSearchPage> createState() => _ScreenSearchPageState();
}

class _ScreenSearchPageState extends State<ScreenSearchPage> {
  void sendEventToNative(String action) async {
    try {
      await platform.invokeMethod('onFlutterEvent', action);
    } on PlatformException catch (e) {
      print("Failed to send event: '${e.message}'.");
    }
  }

  String name = "";

  @override
  Widget build(BuildContext context) {
    platform.setMethodCallHandler((call) async {
      if (call.method == "setData") {
        final Map args = call.arguments;

        setState(() {
          name = args["title"];
        });
      }
    });

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[Text(name)],
        ),
      ),
    );
  }
}
