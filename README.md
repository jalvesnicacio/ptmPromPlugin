# PtmPromPlugin

A ProM plugin for trend deviation detection in event logs, built on top
of the [BeepBeep](http://liflab.github.io/beepbeep-3/) event stream
processing library and its
[PatTheMiner](https://github.com/liflab/PatTheMiner) palette for
real-time data mining.

Given an event log in XES format, PtmPromPlugin detects when traces
deviate from an expected behavior trend, using configurable trend
functions, distance metrics, and thresholds — all accessible via a
step-by-step wizard in the ProM UITopia interface.

## Important: this is a fork of ProM

This repository is **not** a standard ProM plugin installable via the
ProM package manager. It is a **fork of the ProM framework** with the
PtmPromPlugin included. Installation requires setting up the full ProM
development environment in Eclipse, as described below.

## 1. Prerequisites

- Java JDK 8 (recommended: Eclipse Temurin 8)
- Eclipse IDE for Java Developers (tested with Eclipse 2026-06)
- Apache Ant 1.10+
- IvyDE plugin for Eclipse —
  [download here](https://ant.apache.org/ivy/ivyde/download.cgi)
- Subclipse plugin for Eclipse (Subversion support) —
  [download here](https://github.com/subclipse/subclipse/wiki)

## 2. Installation

1. Clone this repository:
   ```
   git clone https://github.com/jalvesnicacio/ptmPromPlugin.git
   ```
2. Open Eclipse and import the project:
   **File → Import → Existing Projects into Workspace**
3. Resolve dependencies via IvyDE:
   Right-click on the project → **Ivy → Resolve**

## 3. Running PtmPromPlugin

1. In Eclipse, open the **Run Configurations** dialog
2. Find or create a launch configuration with the following settings:
   - **Main tab**
     - Project: `ProM`
     - Main class: `org.processmining.contexts.uitopia.UI`
   - **Arguments tab**
     - VM arguments: `-ea -Xmx1G -Djava.library.path=.lib`
3. Run the configuration — select **ProM with UITopia** from the list
4. In ProM, click **Import** (top right) and select your XES file
5. In the import dialog, select **ProM log files (Naive)** and click OK
6. Once the log appears in the Workspace, switch to the **Actions** tab
   (second tab at the top)
7. In the Actions search field, type **beepbeep**
8. Select **Mine Log with BeepBeep** and click **Start**

Sample event logs in XES format are available at
[4TU ResearchData](https://data.4tu.nl).

## 4. Dependencies

- [BeepBeep 3](https://github.com/liflab/beepbeep-3)
- [PatTheMiner](https://github.com/liflab/PatTheMiner)
- [ProM Framework](http://www.promtools.org)