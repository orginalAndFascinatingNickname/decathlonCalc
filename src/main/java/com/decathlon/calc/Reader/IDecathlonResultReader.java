package com.decathlon.calc.Reader;

import com.decathlon.calc.Domain.DecathlonResultEntry;

import java.util.List;

public interface IDecathlonResultReader {
    List<DecathlonResultEntry> readAllEntries() throws Exception;
}
