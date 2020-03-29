# PtmPromPlugin
A plug-in for ProM utilising BeepBeep  PatTheMiner Palette. PtmProMPlugin Project is a plug-in that works in the [ProM program](http://www.promtools.org) and is able to inform when instances of a process are distant from a certain behavior trend.

PTM-ProM works using the [BeepBeep library](http://liflab.github.io/beepbeep-3/) and [PatTheMiner](https://github.com/liflab/PatTheMiner), a real-time data mining palette for the BeepBeep event stream processor.


## 1. Installation
Since PtmPromPlugin is a fork of the [ProM repository](https://svn.win.tue.nl/repos/prom), installation is performed following the ProM installation instructions. So, for configuring Java and Eclipse, see this link [here](https://svn.win.tue.nl/trac/prom/wiki/setup/SettingUpJavaAndEclipse). It is also necessary to install the [IvyDE](https://ant.apache.org/ivy/ivyde/download.cgi) and [Subversion](https://github.com/subclipse/subclipse/wiki) plug-ins in Eclipse.

**PS.:** right click on project name -> Ivy -> Resolve.
 

## 2. Running PtmPromPlugin
Follow the same instructions to [run the Prom](https://svn.win.tue.nl/trac/prom/wiki/setup/RunningProM). Select "ProM Uitopia" from the launch list.

**PS.:** In case "ProM with UITopia" is not on the list:

	1. Select "Run Configurations...".
	2. Find the "Prom with UITopia" launch configuration, or create it with the following details:
		a. Main tab:
			* Project: ProM
			* Main class: org.processmining.contexts.uitopia.UI
		b. Arguments tab:
			* VM arguments: -ea -Xmx1G -Djava.library.path=.lib
	3. Run the "Prom with UITopia" launch configuration.

1. With the program running, click on the "import ..." button. ![Main Screen of ProM][prom_main]
2. Next, select an example file (.xes) in the EventLogs\Chapter_1 project folder.
3. Choose the "ProM Log Files (XESLite - MapDB) import plugin. Click Ok.![][select_import_plugin]
4. Then click on the "Use Resource" button (button with an arrowhead on the right side)![][use_resource]
5. In the Actions window, in the search field, type BeepBeep
6. Select the "Mine a Business Process Log with BeepBeep" plugin. ![][select_ptmplugin]

[prom_main]: img/prom_main.png "Main Screen ProM"
[select_import_plugin]: img/select_import_plugin.png "select import plug-in"
[use_resource]: img/use_resource.png "Use Resource option"
[select_ptmplugin]: img/select_ptmplugin.png "Select Mine a business process log with BeepBeep"