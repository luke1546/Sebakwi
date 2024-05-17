using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class trigger : MonoBehaviour
{
    public GameObject EventSystem;
    private void OnTriggerEnter(Collider other)
    {
        // 들어온 오브젝트의 태그를 확인하거나 다른 방법으로 확인
        if (other.gameObject.CompareTag("ohtTop"))
        {
            StartCoroutine(CallFunctionWithDelay(0.3f));
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.gameObject.CompareTag("ohtTop"))
        {
            EventSystem.GetComponent<CanvasManager>().Hide();
        }
    }
    private IEnumerator CallFunctionWithDelay(float delay)
    {
        yield return new WaitForSeconds(delay);
        EventSystem.GetComponent<CanvasManager>().Show();
    }
}
