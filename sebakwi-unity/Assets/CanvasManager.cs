using System.Collections;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class CanvasManager : MonoBehaviour
{
    public Image greenBox;
    public Image redBox;
    public TMP_Text judgment;
    float time = -2;
    void Start()
    {
        Hide();
    }

    void Update()
    {
        time += Time.deltaTime;
        if (time >= 4)
        {
            time = 0;
            Show();
        }
        if (time > 2)
        {
            Hide();
        }
    }

    private void Show()
    {
        string msg;
        int randomNumber = Random.Range(0, 100);
        if (randomNumber < 90)
        {
            msg = "Good ";
            greenBox.gameObject.SetActive(true); // 대화 상자 활성화
        }
        else
        {
            msg = "Bad ";
            redBox.gameObject.SetActive(true); // 대화 상자 활성화
        }
        float randomValue = Random.Range(0.5f, 0.99f);
        judgment.text = msg + randomValue.ToString("F2");
        judgment.gameObject.SetActive(true);
    }

    // 대화 상자 숨기기
    private void Hide()
    {
        greenBox.gameObject.SetActive(false); // 대화 상자 비활성화
        redBox.gameObject.SetActive(false); // 대화 상자 비활성화
        judgment.gameObject.SetActive(false);
    }
}
