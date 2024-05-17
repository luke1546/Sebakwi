using System.Collections;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class CanvasManager : MonoBehaviour
{
    public Image greenBox1;
    public Image redBox1;
    public Image greenBox2;
    public Image redBox2;
    public Image greenBox3;
    public Image redBox3;
    public Image greenBox4;
    public Image redBox4;
    public TMP_Text detect1;
    public TMP_Text detect2;
    public TMP_Text detect3;
    public TMP_Text detect4;
    void Start()
    {
        Hide();
    }

    void Update()
    {
        // time += Time.deltaTime;
        // if (time >= 4)
        // {
        //     time = 0;
        //     Show();
        // }
        // if (time > 2)
        // {
        //     Hide();
        // }
    }

    public void Show()
    {
        string msg1;
        string msg2;
        string msg3;
        string msg4;
        int randomNumber1 = Random.Range(0, 100);
        int randomNumber2 = Random.Range(0, 100);
        int randomNumber3 = Random.Range(0, 100);
        int randomNumber4 = Random.Range(0, 100);
        if (randomNumber1 < 70)
        {
            msg1 = "Good ";
            greenBox1.gameObject.SetActive(true); // 대화 상자 활성화
        }
        else
        {
            msg1 = "Bad ";
            redBox1.gameObject.SetActive(true); // 대화 상자 활성화
        }

        if (randomNumber2 < 70)
        {
            msg2 = "Good ";
            greenBox2.gameObject.SetActive(true); // 대화 상자 활성화
        }
        else
        {
            msg2 = "Bad ";
            redBox2.gameObject.SetActive(true); // 대화 상자 활성화
        }

        if (randomNumber3 < 70)
        {
            msg3 = "Good ";
            greenBox3.gameObject.SetActive(true); // 대화 상자 활성화
        }
        else
        {
            msg3 = "Bad ";
            redBox3.gameObject.SetActive(true); // 대화 상자 활성화
        }

        if (randomNumber4 < 70)
        {
            msg4 = "Good ";
            greenBox4.gameObject.SetActive(true); // 대화 상자 활성화
        }
        else
        {
            msg4 = "Bad ";
            redBox4.gameObject.SetActive(true); // 대화 상자 활성화
        }

        float randomValue1 = Random.Range(0.5f, 0.99f);
        float randomValue2 = Random.Range(0.5f, 0.99f);
        float randomValue3 = Random.Range(0.5f, 0.99f);
        float randomValue4 = Random.Range(0.5f, 0.99f);
        msg1 += randomValue1.ToString("F2");
        detect1.text = msg1;
        detect1.gameObject.SetActive(true);

        msg2 += randomValue2.ToString("F2");
        detect2.text = msg2;
        detect2.gameObject.SetActive(true);

        msg3 += randomValue3.ToString("F2");
        detect3.text = msg3;
        detect3.gameObject.SetActive(true);

        msg4 += randomValue4.ToString("F2");
        detect4.text = msg4;
        detect4.gameObject.SetActive(true);
    }

    // 대화 상자 숨기기
    public void Hide()
    {
        greenBox1.gameObject.SetActive(false); // 대화 상자 비활성화
        redBox1.gameObject.SetActive(false); // 대화 상자 비활성화
        detect1.gameObject.SetActive(false);

        greenBox2.gameObject.SetActive(false); // 대화 상자 비활성화
        redBox2.gameObject.SetActive(false); // 대화 상자 비활성화
        detect2.gameObject.SetActive(false);

        greenBox3.gameObject.SetActive(false); // 대화 상자 비활성화
        redBox3.gameObject.SetActive(false); // 대화 상자 비활성화
        detect3.gameObject.SetActive(false);

        greenBox4.gameObject.SetActive(false); // 대화 상자 비활성화
        redBox4.gameObject.SetActive(false); // 대화 상자 비활성화
        detect4.gameObject.SetActive(false);
    }
}
