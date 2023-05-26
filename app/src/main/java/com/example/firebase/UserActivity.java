//package com.example.firebase;
//
//import static java.lang.Integer.parseInt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.PriorityQueue;
//
//public class UserActivity extends AppCompatActivity {
//
//    int cnt = 0;
//    WifiInfo wifiInfo;
//    String a = "1",b = "1", c = "5", d = "5";
//    private FirebaseFirestore firestore;
//    private Bitmap image1;
//    private Paint mPaint;
//
//    ValueHandler handler = new ValueHandler();
//    private int x, y;
//
//    // ArrayList -> HashMap 변환
//    ArrayList<WifiInfo> wifis = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_main);
//        //setContentView(R.layout.activity_animation_canvas_test);
//        setContentView(new MyView(this));
//        firestore = FirebaseFirestore.getInstance();
//        //setContentView(R.layout.activity_main);
//
////        WifiInfo wifiInfo = new WifiInfo("5", "5");
////        firestore = FirebaseFirestore.getInstance();
////        //DB에 넣기 야미
////        firestore.collection("classrooms").document("403").set(wifiInfo);
////        firestore.collection("classrooms").document("402").set();
//
//        Button scanner = findViewById(R.id.scanner);
//    }
//
//    public class MyView extends View implements com.example.firebase.MyView {
//
//
//        int[][] grid = {
//                {0, 0, 0},
//                {0, -1, 0},
//                {0, -1, 0},
//                {0, 0, 0},
//                {0, 0, 0},
//                {0, 0, 0},
//                {0, 0, 0}};
//
//        int start_x = 0;
//        int start_y = 0;
//        int end_x = 4;
//        int end_y = 2;
//
//        public MyView(Context context) {
//            super(context);
//            mPaint = new Paint();
//
////            AStar aStar = new AStar(grid, start_x, start_y, end_x, end_y);
////            List<Node> path = aStar.findPath();
//
////            if (path != null) {
////                for (Node node : path) {
////                    System.out.println("(" + node.x + ", " + node.y + ")");
////                    draw(canvas,node.x,node.y);
////                }
////            } else {
////                System.out.println("No path found.");
////            }
//
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawBitmap(image1, 0, 0, null);
//            //Log.e("tga",x + " " + y);
//            super.onDraw(canvas);
//
//            CollectionReference collectionReference = firestore.collection("classrooms");
//            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if(task.isSuccessful()){
//                        for(QueryDocumentSnapshot documentSnapshots : task.getResult()){
//                            if(documentSnapshots.getData().get("x") != null && documentSnapshots.getData().get("y") != null){
//                                x = Integer.parseInt(documentSnapshots.getData().get("x").toString());
//                                y = Integer.parseInt(documentSnapshots.getData().get("y").toString());
//                                Log.e("tga", "eeeeeeeeeeee" + x + " " + y);
////                                canvas.drawBitmap(image1, 0, 0, null);
////                                canvas.drawRect(x*100, y*100, x*100+10, y*100+10, mPaint); //캔버스에 빨간 사각형 그리기
//                            }
//                        }
//                    }
//                }
//            });
//
//            Log.e("tga", "ffffffffffffffff" + x + " " + y);
//
//            firestore.collection("classrooms").document("402").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if(task.isSuccessful()){
//
//                        x = Integer.parseInt(task.getResult().getData().get("x").toString());
//                        y = Integer.parseInt(task.getResult().getData().get("y").toString());
////                        Log.e("tga",x + " " + y);
////                        canvas.drawRect(x+20, y+20, x+50, y+50, mPaint); //캔버스에 빨간 사각형 그리기
////                        AStar aStar = new AStar(grid, start_x, start_y, end_x, end_y);
////                        List<Node> path = aStar.findPath();
//////////////////////////////////////////// node.parent 불러오기, Node node: path 길이 찾기 및 node 불러오기
////                        if (path != null) {
////                            //DB에 꺼내오기
/////////////////////////////////////////
////                            //canvas.drawLine(parseFloat(a) * 10 + 50, parseFloat(b) * 10 + 50, parseFloat(a) * 30 + 50, parseFloat(b) * 30 + 50, mPaint); //캔버스에 빨간 사각형 그리기
////                            //canvas.drawLine(parseFloat(c) * 10 + 50, parseFloat(d) * 10 + 50, parseFloat(c) * 30 + 50, parseFloat(d) * 30 + 50, mPaint); //캔버스에 빨간 사각형 그리기
//////                for (Node node : path)
////                            for (int i = 0; i < path.size() - 1; i++) {
////                                System.out.println("(" + path.get(i).y + ", " + path.get(i).x + ")");
////                                //canvas.drawLine(path.get(i).y * 300 + 50, path.get(i).x * 300 + 50, path.get(i + 1).y * 300 + 50, path.get(i + 1).x * 300 + 50, mPaint); //캔버스에 빨간 사각형 그리기
////                            }
////                        } else {
////                            System.out.println("No path found.");
////                        }
//                    }
//                    Log.e("tga","test");
//                    Log.e("tga",x + " " + y);
//                    cnt = 1;
//                    canvas.drawBitmap(image1, 0, 0, null);
//                    canvas.drawRect(x+20, y+20, x+50, y+50, mPaint); //캔버스에 빨간 사각형 그리기
//                }
//            });
//            //canvas.drawRect(100, 100, 100 + 10, 100 + 10, mPaint); //캔버스에 빨간 사각형 그리기
//        }
//    }
//
//    class BackgroundThread extends Thread {
//        int value = 0;
//        boolean running = false;
//        public void run() {
//            running = true;
//            while(running) {
//                value += 1;
//                Message message = handler.obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("value",value);
//                message.setData(bundle);
//                handler.sendMessage(message);
//
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {}
//            }
//        }
//    }
//
//    class ValueHandler extends Handler {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//
//            Bundle bundle = msg.getData();
//
//        }
//    }
//}
//
//
//
//
//class Node {
//    int x;
//    int y;
//    int f;
//    int g;
//    int h;
//    Node parent;
//
//    public Node(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
//class AStar {
//    private int[][] grid;
//    private boolean[][] visited;
//    private PriorityQueue<Node> openSet;
//    private int start_x, start_y;
//    private int end_x, end_y;
//    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//
//    public AStar(int[][] grid, int start_x, int start_y, int end_x, int end_y) {
//        this.grid = grid;
//        this.start_x = start_x;
//        this.start_y = start_y;
//        this.end_x = end_x;
//        this.end_y = end_y;
//        visited = new boolean[grid.length][grid[0].length];
//        openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));
//    }
//
//    public List<Node> findPath() {
//        Node startNode = new Node(start_x, start_y);
//        startNode.g = 0;
//        startNode.h = calculateHeuristic(start_x, start_y);
//        startNode.f = startNode.g + startNode.h;
//        openSet.add(startNode);
//
//        while (!openSet.isEmpty()) {
//            Node currentNode = openSet.poll();
//            visited[currentNode.x][currentNode.y] = true;
//
//            if (currentNode.x == end_x && currentNode.y == end_y) {
//                return reconstructPath(currentNode);
//            }
//
//            for (int[] direction : directions) {
//                int neighborX = currentNode.x + direction[0];
//                int neighborY = currentNode.y + direction[1];
//
//                if (isValidNeighbor(neighborX, neighborY)) {
//                    int tentativeGScore = currentNode.g + 1;
//
//                    if (tentativeGScore < grid[neighborX][neighborY] || !visited[neighborX][neighborY]) {
//                        Node neighborNode = new Node(neighborX, neighborY);
//                        neighborNode.g = tentativeGScore;
//                        neighborNode.h = calculateHeuristic(neighborX, neighborY);
//                        neighborNode.f = neighborNode.g + neighborNode.h;
//                        neighborNode.parent = currentNode;
//
//                        if (!openSet.contains(neighborNode)) {
//                            openSet.add(neighborNode);
//                        }
//                    }
//                }
//            }
//        }
//
//        return null; // No path found
//    }
//
//    private int calculateHeuristic(int x, int y) {
//        // Manhattan distance heuristic
//        return Math.abs(x - end_x) + Math.abs(y - end_y);
//    }
//
//    private boolean isValidNeighbor(int x, int y) {
//        // Check if the neighbor is within the grid and not blocked
//        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != -1;
//    }
//
//    private List<Node> reconstructPath(Node node) {
//        List<Node> path = new ArrayList<>();
//        while (node != null) {
//            path.add(0, node);
//            node = node.parent;
//        }
//        return path;
//    }
//}