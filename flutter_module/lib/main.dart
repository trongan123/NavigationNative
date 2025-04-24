import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'present_one.dart';
import 'present_two.dart';
import 'screen_one.dart';
import 'screen_search.dart';
import 'screen_three.dart';
import 'screen_two.dart';
import 'screen_profile.dart';

void main() =>
    runApp(
      MaterialApp(
          navigatorKey: navKey,
          initialRoute: '/',
          routes: {
            '/': (context) => const MyHomePage(title: 'Flutter Demo Home Page'),
            '/screen_one': (context) => ScreenOnePage(title: 'Screen One'),
            '/screen_two': (context) => ScreenTwoPage(title: 'Screen Two'),
            '/screen_three': (context) =>
                ScreenThreePage(title: 'Screen Three'),
            '/present_one': (context) => PresentOnePage(title: 'Present One'),
            '/present_two': (context) => PresentTwoPage(title: 'Present Two'),
            '/screen_search': (context) =>
                ScreenSearchPage(title: 'Screen Search'),
            '/screen_profile': (context) =>
                ScreenProfilePage(title: 'Screen Profile'),
          }
      ),
    );

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MyHomePage(title: 'Flutter Demo Home Page');
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

const platform = MethodChannel('com.navigation.flutter/main');

class _MyHomePageState extends State<MyHomePage> {
  @override
  void initState() {
    super.initState();
    platform.setMethodCallHandler((call) async {
      if (call.method == "showBottomSheet") {
        _showBottomSheet();
      }
    });
  }

  void _showBottomSheet() {
    showModalBottomSheet(
        context: navKey.currentContext!,
        builder:
            (_) => _CustomBottomSheetView()
    );
  }

  void sendEventToNative(String eventName) async {
    try {
      await platform.invokeMethod('navigation', eventName);
    } on PlatformException catch (e) {
      print("Failed to send event: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text('Navigate Native to Flutter:'),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Present");
              },
              child: const Text("Present"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Push");
              },
              child: const Text("Push"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("BottomSheet");
              },
              child: const Text("Bottom Sheet"),
            ),
            ElevatedButton(
              onPressed: () {
                sendEventToNative("Notification");
              },
              child: const Text("Show Notification"),
            ),
            const SizedBox(height: 20),
            const Text('Navigate Flutter to Flutter:'),
            const SizedBox(height: 10),
            ElevatedButton(
              onPressed: () {
                // Xử lý chổ này
                Navigator.of(context).pushNamed('/screen_profile');
              },
              child: const Text("Flutter to Flutter"),
            ),
          ],
        ),
      ),
    );
  }
}

class _CustomBottomSheetView extends StatelessWidget {
  const _CustomBottomSheetView();

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 200,
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text('BottomSheet Flutter:'),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: () {
              Navigator.of(context).pop();
            }, child: const Text("Back")),
          ],
        ),
      ),
    );
  }
}