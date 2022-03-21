package vip.jayson.gui;

import vip.jayson.config.CAAData;
import vip.jayson.main.RunTool;
import vip.jayson.readData.ReadInterfaceFileConfig;
import vip.jayson.util.LogConsole;

import java.awt.event.ActionEvent;

public class Listener {
    private void GenerateInfoActionPerformed(ActionEvent e) {
        new Thread(new Runnable() {//开辟一个工作线程
            @Override
            public void run() {
                LogConsole.info("-------CAA200-2 辅助测试工具--------");
                String mainPath = RunTool.projectRootPath + "\\" + RunTool.testCase + "\\";
                CAAData.stationNumList.add("MAIN");
                ReadInterfaceFileConfig.getAllInputFile(mainPath, RunTool.mainConfigName, RunTool.codeDataName);
                RunTool.generateHelpInfo();
            }
        }).start();
        // TODO add your code here
        // TODO add your code here

    }

    private void RunCAAActionPerformed(ActionEvent e) {
        new Thread(new Runnable() {//开辟一个工作线程
            @Override
            public void run() {
                RunTool.lctRunCAA();
            }
        }).start();

    }

    private void CompareADSActionPerformed(ActionEvent e) {
        new Thread(new Runnable() {//开辟一个工作线程
            @Override
            public void run() {
                RunTool.compareCAA();
            }
        }).start();

    }

    private void CompareBinActionPerformed(ActionEvent e) {
        new Thread(new Runnable() {//开辟一个工作线程
            @Override
            public void run() {
                RunTool.compareBIN();
            }
        }).start();
    }
}
