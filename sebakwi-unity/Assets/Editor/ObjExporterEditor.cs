using System;
using System.IO;
using UnityEditor;
using UnityEngine;

public class ObjExporter : EditorWindow
{
    [MenuItem("Tools/Export Selected Object to OBJ")]
    public static void ExportSelectedObjectToObj()
    {
        GameObject selectedObject = Selection.activeGameObject;

        if (selectedObject == null)
        {
            Debug.LogWarning("No object selected for export.");
            return;
        }

        string directory = Application.dataPath + "/ExportedObjs/";
        Directory.CreateDirectory(directory);

        string filename = directory + selectedObject.name + ".obj";

        using (StreamWriter writer = new StreamWriter(filename))
        {
            ExportMesh(writer, selectedObject);
        }

        Debug.Log("Exported " + selectedObject.name + " to " + filename);
    }

    private static void ExportMesh(StreamWriter writer, GameObject obj)
    {
        MeshFilter[] meshFilters = obj.GetComponentsInChildren<MeshFilter>();
        MeshRenderer[] meshRenderers = obj.GetComponentsInChildren<MeshRenderer>();

        if (meshFilters.Length == 0)
        {
            Debug.LogWarning("No mesh filters found on the selected object.");
            return;
        }

        int vertexOffset = 0;
        int normalOffset = 0;

        foreach (MeshFilter mf in meshFilters)
        {
            Mesh mesh = mf.sharedMesh;

            if (mesh == null) continue;

            Vector3[] vertices = mesh.vertices;
            Vector3[] normals = mesh.normals;
            int[] triangles = mesh.triangles;

            foreach (Vector3 v in vertices)
            {
                writer.WriteLine($"v {v.x} {v.y} {v.z}");
            }

            foreach (Vector3 n in normals)
            {
                writer.WriteLine($"vn {n.x} {n.y} {n.z}");
            }

            for (int i = 0; i < triangles.Length; i += 3)
            {
                writer.WriteLine($"f {triangles[i] + 1 + vertexOffset}//{triangles[i] + 1 + normalOffset} {triangles[i + 1] + 1 + vertexOffset}//{triangles[i + 1] + normalOffset} {triangles[i + 2] + 1 + vertexOffset}//{triangles[i + 2] + normalOffset}");
            }

            vertexOffset += vertices.Length;
            normalOffset += normals.Length;
        }
    }
}
