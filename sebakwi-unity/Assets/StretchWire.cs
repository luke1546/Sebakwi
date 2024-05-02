using UnityEngine;

public class StretchWire : MonoBehaviour
{
    public GameObject OHTMove;
    private float stretchSpeed = 10; // 와이어가 늘어나는 속도
    private float maxStretch = 12; // 최대 길이
    private Vector3 maxPosition;

    private float originalLength; // 초기 길이
    private Vector3 originalPosition; // 초기 위치
    private bool stretchFlag = false;
    private bool dropFlag = false;


    void Start()
    {
        originalLength = transform.localScale.y;
        originalPosition = transform.localPosition;
    }

    void Update()
    {
        if (stretchFlag)
        {
            if (!dropFlag && transform.localScale.y < maxStretch)
            {
                // 스케일 조정
                float newYScale = transform.localScale.y + stretchSpeed * Time.deltaTime;
                transform.localScale = new Vector3(transform.localScale.x, newYScale, transform.localScale.z);

                // 위치 조정
                float deltaY = (newYScale - originalLength) / 2;
                transform.localPosition = new Vector3(originalPosition.x, originalPosition.y - (10.9f * deltaY), originalPosition.z);
            }
            if (transform.localScale.y >= maxStretch)
            {
                dropFlag = true;
                maxPosition = transform.localPosition;
            }

            if (dropFlag && transform.localScale.y > originalLength) //다시 줄이기
            {
                // 스케일 조정
                float newYScale = transform.localScale.y - stretchSpeed * Time.deltaTime;
                transform.localScale = new Vector3(transform.localScale.x, newYScale, transform.localScale.z);

                // 위치 조정
                float deltaY = (maxStretch - newYScale) / 2;
                transform.localPosition = new Vector3(originalPosition.x, maxPosition.y + (10.9f * deltaY) + 0.4f, originalPosition.z);
            }

            if (transform.localScale.y <= originalLength && dropFlag)
            {
                OHTMove.GetComponent<OHTMove>().move();
                dropFlag = false;
                stretchFlag = false;
            }
        }
    }
    public void stretch()
    {
        stretchFlag = true;
    }
}
