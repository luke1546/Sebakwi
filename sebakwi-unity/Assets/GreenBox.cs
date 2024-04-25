using UnityEngine;

public class GreenBox : MonoBehaviour
{
    // 초록색 박스의 크기 설정
    public Vector2 boxSize = new Vector2(100, 100);

    // 초록색 박스를 그리는 함수
    void OnGUI()
    {
        GUI.color = Color.green; // 초록색으로 설정
        GUI.Box(new Rect(10, 10, boxSize.x, boxSize.y), "Green Box"); // 초록색 박스 그리기
    }
}
