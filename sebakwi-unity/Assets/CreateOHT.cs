using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CreateOHT : MonoBehaviour
{
    public GameObject OHT;
    private double time = 0;
    private Vector3 startPoint = new Vector3(-6.36f, 5.4f, -19f);

    // Start is called before the first frame update
    void Start()
    {
        Instantiate(OHT, startPoint, Quaternion.identity);
    }

    // Update is called once per frame
    void Update()
    {
        time += Time.deltaTime;

        // 10초 이상일 때마다 OHT 생성
        if (time >= 4.0)
        {
            Instantiate(OHT, startPoint, Quaternion.identity);
            time = 0; // 시간 초기화
        }
    }
}
