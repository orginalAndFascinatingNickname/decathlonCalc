package com.decathlon.calc.Writer;

import com.decathlon.calc.Domain.DecathlonResultEntry;

import java.util.List;

public interface IDecathlonResultWriter {
    void writeAllResultEntries(List<DecathlonResultEntry> resultEntries);
}
