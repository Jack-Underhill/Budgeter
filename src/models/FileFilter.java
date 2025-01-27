package src.models;

import java.io.File;
import java.util.ArrayList;

public class FileFilter
{
    public FileFilter(String folderPath)
    {
        m_FolderPath = folderPath;
        m_CSVFiles = new ArrayList<>();
    }

    public boolean run()
    {
        File folder = new File(m_FolderPath);

        // Validate Directory
        if(folder.isDirectory())
        {
            File[] files = folder.listFiles();

            // Verify it Contains files
            if(files != null)
            {
                for(File file : files)
                {
                    // Validate File
                    if(file.isFile())
                    {
                        String fileName = stripFileType(file.getName());

                        // Loads only .csv files
                        if(!fileName.isEmpty())
                        {
                            m_CSVFiles.add(file);
                        }
                    }
                }

                if(!m_CSVFiles.isEmpty())
                {
                    return true;
                }
            }
        }
        
        return false;
    }

    public String getDirectory()
    {
        return m_FolderPath;
    }

    public ArrayList<File> getFiles()
    {
        return m_CSVFiles;
    }

    public String stripFileType(String fileName)
    {
        int index = fileName.lastIndexOf('.');

        if(index > 0 && index < fileName.length() - 1 && fileName.substring(index + 1).equals("CSV"))
        {
            return fileName.substring(0, index);
        }
        else
        {
            return "";
        }
    }

    /* ---------------------------------------- Private ---------------------------------------- */

    private String m_FolderPath;
    private ArrayList<File> m_CSVFiles;
}
