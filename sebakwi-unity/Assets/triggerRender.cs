using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class triggerRender : MonoBehaviour
{
    public GameObject trigger;
    // Start is called before the first frame update
    void Start()
    {
        if (trigger != null)
        {
            Renderer renderer = trigger.GetComponent<Renderer>();
            if (renderer != null)
            {
                renderer.enabled = false;
            }
        }
    }

    // Update is called once per frame
    void Update()
    {

    }
}
